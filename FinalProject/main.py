import os
import numpy as np
import pretty_midi

# Set the directory where your MIDI files are stored
midi_dir = "data/"

# Set the length of each sequence of notes and the number of sequences to generate
seq_length = 1000
num_sequences = 1000

# Set the output directory for the preprocessed data
output_dir = "output/"

# Define a function to extract the relevant features from a MIDI file
def extract_features(midi_path):
    midi_data = pretty_midi.PrettyMIDI(midi_path)
    notes = midi_data.instruments[0].notes
    pitches = [note.pitch for note in notes]
    durations = [note.end - note.start for note in notes]
    velocities = [note.velocity for note in notes]
    features = np.array([pitches, durations, velocities]).T
    return features

# Collect the features from all the MIDI files in the directory
features_list = []
for filename in os.listdir(midi_dir):
    if filename.endswith(".mid"):
        midi_path = os.path.join(midi_dir, filename)
        features = extract_features(midi_path)
        features_list.append(features)

# Concatenate all the features into a single numpy array
all_features = np.concatenate(features_list, axis=0)

# Normalize the features to a common scale
pitches_mean = np.mean(all_features[:, 0])
pitches_std = np.std(all_features[:, 0])
durations_mean = np.mean(all_features[:, 1])
durations_std = np.std(all_features[:, 1])
velocities_mean = np.mean(all_features[:, 2])
velocities_std = np.std(all_features[:, 2])
all_features[:, 0] = (all_features[:, 0] - pitches_mean) / pitches_std
all_features[:, 1] = (all_features[:, 1] - durations_mean) / durations_std
all_features[:, 2] = (all_features[:, 2] - velocities_mean) / velocities_std

# Split the preprocessed data into training and validation sets
num_training_sequences = int(num_sequences * 0.8)
training_features = []
validation_features = []
for i in range(num_sequences):
    start_idx = np.random.randint(0, len(all_features) - seq_length - 1)
    sequence = all_features[start_idx:start_idx+seq_length]
    if i < num_training_sequences:
        training_features.append(sequence)
    else:
        validation_features.append(sequence)

# Save the preprocessed data to numpy arrays
training_features = np.array(training_features)
validation_features = np.array(validation_features)
np.save(os.path.join(output_dir, "training_features.npy"), training_features)
np.save(os.path.join(output_dir, "validation_features.npy"), validation_features)
np.save(os.path.join(output_dir, "pitches_mean.npy"), pitches_mean)
np.save(os.path.join(output_dir, "pitches_std.npy"), pitches_std)
np.save(os.path.join(output_dir, "durations_mean.npy"), durations_mean)
np.save(os.path.join(output_dir, "durations_std.npy"), durations_std)
np.save(os.path.join(output_dir, "velocities_mean.npy"), velocities_mean)
np.save(os.path.join(output_dir, "velocities_std.npy"), velocities_std)
