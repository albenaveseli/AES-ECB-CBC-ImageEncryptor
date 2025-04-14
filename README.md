# Image Encryption and Decryption with AES (ECB & CBC Modes)

This project demonstrates the encryption and decryption of images using the **AES** (Advanced Encryption Standard) algorithm in **Electronic Codebook (ECB)** and **Cipher Block Chaining (CBC)** modes. The image is first read from the disk, then encrypted using both modes and written back to disk. Additionally, the encrypted image is decrypted back to its original form using the same encryption methods.

## Table of Contents

- [Overview](#overview)
- [How It Works](#how-it-works)
- [Prerequisites](#prerequisites)
- [How to Run](#how-to-run)


## Overview

This project focuses on demonstrating the AES encryption in two different modes:
- **ECB (Electronic Codebook)**: This is the simpler of the two modes, where the plaintext is divided into blocks and each block is encrypted independently. While faster, it is less secure because identical plaintext blocks will yield identical ciphertext blocks.
- **CBC (Cipher Block Chaining)**: This mode improves security by XOR'ing each block of plaintext with the previous ciphertext block before encryption. It provides stronger security because identical plaintext blocks result in different ciphertext blocks.

### Features:
- Image encryption and decryption using AES.
- Support for both ECB and CBC modes.
- Padding applied to image data for AES compatibility.
- Easy-to-follow output paths for encrypted and decrypted images.

## How It Works

1. **Image Reading**: The image is read as raw bytes from the disk.
2. **Padding**: To ensure the image data can be encrypted, padding is applied to the data to make its size a multiple of the AES block size (16 bytes).
3. **Encryption**: The image data is encrypted using the AES algorithm in both ECB and CBC modes with a 16-byte key and an initialization vector (IV) for CBC mode.
4. **Decryption**: The encrypted image is decrypted using the same key and IV (for CBC) to return the image to its original state.
5. **File Writing**: The encrypted and decrypted images are written back to the disk as new files.

## Prerequisites

Make sure you have the following:
- **Java Development Kit (JDK)**: Version 8 or later.
- **Java IDE**: Such as IntelliJ IDEA or Eclipse.
- **Image File**: A valid `.bmp` image file to encrypt.

## How to Run

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/your-username/repository-name.git
   cd repository-name
