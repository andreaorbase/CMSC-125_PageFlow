# PageFlow: Page Replacement Algorithm Simulator

## Table of Contents
- [Overview](#overview)
- [Algorithms](#algorithms)
- [Features](#features)
- [Files and Directories](#files-and-directories)
- [Getting Started](#getting-started)
  - [Prerequites](#prerequisites)
  - [User System Flow](#user-system-flow)
  - [Input Format](#input-format)
- [Contribution](#contribution)
  - [License](#license)

## Overview
**PageFlow** is a visual simulator for page replacement algorithms designed to help users understand how different strategies manage memory. It graphically illustrates how pages are loaded, replaced, and evicted from memory using the algorithms mentioned below. Users can input custom reference strings, adjust frame sizes, track hits/misses, and step through each operation to see how faults occur. PageFlow is ideal for anyone who want an interactive way to grasp memory management concepts in operating systems.

## Algorithms
- FIFO (First-In, First-Out)  
- LRU (Least Recently Used)  
- OPT (Optimal Page Replacement)  
- SC (Second Chance)  
- ESC (Enhanced Second Chance)  
- LFU (Least Frequently Used)  
- MFU (Most Frequently Used)

## Features
- Interactive Java Swing GUI  
- Frame-by-frame simulation with animation
- Adjustable simulation speed
- Import page references via `.txt` file
- Generate random input
- Export results as **PNG** or **PDF**  
- Real-time Hit/Miss indicators
- Fault counters for each algorithm
- Side-by-side comparison of all 7 algorithms

## Getting Started

### Prerequisites

To run **PageFlow**, ensure the following are installed or available:

- **Java Development Kit (JDK) 21** or later  
- A system that supports running `.exe` or `.jar` files  
- *Optional:* Java Runtime Environment (JRE) for running `.jar` without compiling

### Run Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/andreaorbase/CMSC-125_PageFlow.git
   ```

2. Open the project in your IDE and run `PageFlow.java`.

Or compile manually:

```bash
javac -cp . src/**/*.java
java -cp src PageFlow
```

> Make sure the `resources/gui/` folder (images, sounds, fonts) is on your classpath when running.

---

### Input Format (For Import Files)

```
3
FIFO
--
7 0 1 2 0 3 0 4
```

Where:
- `3` is the number of frames
- `FIFO` is the selected algorithm
- `7 0 1...` is the reference string

---

## Contribution
Developed by: *EMILINE ACOMPAÑADO, ANDREA ORBASE, RICHARD BISCANTE*  
For: *CMSC 125*  

### License
This project is open-source and intended for academic and educational use.  
Feel free to reuse with attribution.
