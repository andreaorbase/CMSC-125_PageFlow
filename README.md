# 📄 PageFlow: Page Replacement Algorithm Visual Simulator

**PageFlow** is a feature-rich Java GUI application that simulates and visualizes seven classic page replacement algorithms:

- FIFO (First-In, First-Out)  
- LRU (Least Recently Used)  
- OPT (Optimal Page Replacement)  
- SC (Second Chance)  
- ESC (Enhanced Second Chance)  
- LFU (Least Frequently Used)  
- MFU (Most Frequently Used)

It offers a visual and interactive way to understand how these algorithms manage memory, track hits/misses, and perform under different conditions.

---

## 🖥️ Features

- 🎮 Interactive Java Swing GUI  
- 🧠 Frame-by-frame simulation with animation  
- 🎯 Hit/Miss feedback and fault counters  
- 📁 Import page references via `.txt` file  
- 🔀 Generate random input  
- ⏱️ Adjustable simulation speed  
- 📊 Compare all 7 algorithms side-by-side  
- 📤 Export results as **PNG** or **PDF**  
- 🔊 Sound effects and background music

---

## 🚀 Getting Started

### ✅ Requirements

- Java JDK 8 or higher
- IDE (IntelliJ, Eclipse, etc.) or terminal setup

### ▶️ Run Instructions

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

> ⚠️ Make sure the `resources/gui/` folder (images, sounds, fonts) is on your classpath when running.

---

## 📄 Input Format (For Import Files)

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

## 👨‍💻 Author & Acknowledgments

Developed by: *EMILINE ACOMPAÑADO, ANDREA ORBASE, RICHARD BISCANTE*  
For: *CMSC 125*  

---

## 📃 License

This project is open-source and intended for academic and educational use.  
Feel free to reuse with attribution.
