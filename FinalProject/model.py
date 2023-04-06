




import tensorflow as tf
import numpy as np
import pretty_midi

# Define the dimensions of the input and output data
input_dim = 3  # pitches, durations, velocities
output_dim = input_dim
sequence_length = 500

# # Load the preprocessed data from the numpy arrays
training_features = np.load("output/training_features.npy")
validation_features = np.load("output/validation_features.npy")

# Normalize the data
pitches_mean = np.load("output/pitches_mean.npy")
pitches_std = np.load("output/pitches_std.npy")
durations_mean = np.load("output/durations_mean.npy")
durations_std = np.load("output/durations_std.npy")
velocities_mean = np.load("output/velocities_mean.npy")
velocities_std = np.load("output/velocities_std.npy")

# # Reshape the training data
num_sequences = training_features.shape[0] // sequence_length
training_features = training_features[:num_sequences * sequence_length]
training_features = training_features.reshape((-1, sequence_length, input_dim))

# Define the architecture of the model
model = tf.keras.Sequential([
    tf.keras.layers.SimpleRNN(128, input_shape=(None, input_dim), return_sequences=True),
    tf.keras.layers.Dense(output_dim)
])

# Compile the model with a mean squared error loss function and an Adam optimizer
model.compile(loss="mse", optimizer="adam")


# Define the number of epochs and batch size
num_epochs = 50
batch_size = 32

# Define callbacks to save the best model during training based on the validation loss
model_checkpoint = tf.keras.callbacks.ModelCheckpoint("best_model.h5", save_best_only=True)
# tf.config.run_functions_eagerly(True)
# Train the model on the preprocessed data
history = model.fit(training_features, training_features, 
                    validation_data=(validation_features, validation_features),
                    batch_size=batch_size, epochs=num_epochs,
                    callbacks=[model_checkpoint])
# tf.config.run_functions_eagerly(False)
# # Load the saved model for generation
model = tf.keras.models.load_model("best_model.h5")

# Generate new sequences of notes using the trained model
num_sequences = 10
generated_sequences = []
for i in range(num_sequences):
    seed_sequence = np.zeros((1, sequence_length, input_dim))
    generated_sequence = np.zeros((sequence_length, output_dim))
    if i < sequence_length:
        # randomly initialize the first few notes of the generated sequence
        generated_sequence[i, :] = np.random.normal(size=(output_dim,))
        
    max_iterations = 1000 # Set the maximum number of iterations for the generation loop
    for j in range(sequence_length):
        # Predict the next note based on the previous sequence
        next_note = model.predict(seed_sequence)[0][-1]
        # Add the predicted note to the generated sequence
        generated_sequence[j] = next_note
        # Update the seed sequence with the new note
        new_seed_sequence = np.zeros_like(seed_sequence)
        new_seed_sequence[:, :-1, :] = seed_sequence[:, 1:, :]
        new_seed_sequence[:, -1, :] = next_note
        seed_sequence = new_seed_sequence
    generated_sequences.append(generated_sequence)
# Create a PrettyMIDI object to store the generated music
midi = pretty_midi.PrettyMIDI()

# Create an Instrument object to represent the generated notes
instrument = pretty_midi.Instrument(program=0)

# Convert the generated sequences into notes and add them to the Instrument object
for sequence in generated_sequences:
    for note in sequence:
        pitch = note[0] * pitches_std + pitches_mean
        duration = note[1] * durations_std + durations_mean
        velocity = note[2] * velocities_std + velocities_mean
        note = pretty_midi.Note(
            velocity=int(velocity), pitch=int(pitch), start=0, end=duration)
        instrument.notes.append(note)

# Add the Instrument object to the PrettyMIDI object
midi.instruments.append(instrument)

# Write the MIDI file to disk
midi.write('generated_music.mid')
