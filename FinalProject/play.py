import pygame

pygame.init()

# Load the MIDI file
pygame.mixer.music.load('/Users/deeppatel/Downloads/generated_music_1.mid')

# Play the MIDI file
pygame.mixer.music.play()

# Wait for the MIDI file to finish playing
while pygame.mixer.music.get_busy():
    continue
