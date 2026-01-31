**RC Plane Engineering Wizard ✈️**
==================================

**Version:** 1.7

**Status:** Completed

The **RC Plane Engineering Wizard** is a modern **Android application** designed to guide hobbyists and engineering students through the complex process of designing a radio-controlled aircraft.

Unlike standard calculators that require you to know your geometry beforehand, this app functions as a sequential "wizard." It starts with your constraints (Mission, Weight, Power) and **automatically calculates** the required airframe geometry (Wing Span, Chord, Tail Areas) based on aerodynamic physics formulas.

**🚀 Features**
---------------

*   **Sequential Wizard Flow:** A step-by-step interface that ensures all design requirements are met before moving forward.
    
*   **Mission-Based Presets:** Automatically sets Lift Coefficients () and Tail Volumes based on your flying style (Trainer, Racing, Aerobatics, or Payload).
    
*   **Physics-Driven Sizing:** Calculates wing area, root chord, and tail dimensions using real aerodynamic formulas.
    
*   **Propulsion Analysis:** Specific logic checks to warn if your selected motor/prop combo provides insufficient thrust for your weight.
    
*   **Assembly Guide:** Generates a custom layout guide telling you exactly where to place the wing and the Center of Gravity (CG).
    
*   **Beginner Friendly:** Includes a "Technical Hint" system with tooltips explaining complex terms like Motor KV, Prop Pitch, and Wing Loading.
    

**🛠️ Tech Stack**
------------------

This project is built using modern Android development practices:

*   **Language:** Kotlin
    
*   **UI Framework:** Jetpack Compose (Modern, declarative UI)
    
    *   **Material 3:** For base components and theming.
        
    *   **Custom Design System:** "Neo-Brutalism" implementation (Bold borders, high contrast, hard shadows).
        
*   **Architecture:** MVVM (Model-View-ViewModel)
    
    *   **ViewModel:** For state management and business logic.
        
    *   **StateFlow:** For reactive data streams.
        
*   **Navigation:** Jetpack Navigation Compose
    
*   **Build System:** Gradle (Kotlin DSL)
    
*   **Minimum SDK:** 24 (Android 7.0 Nougat)
    
*   **Target SDK:** 34 (Android 14)
    

**⚙️ How It Works**
-------------------

The application follows a strict linear flow to ensure engineering validity:

1.  **Mission & Configuration:** You define _how_ you want to fly (e.g., a stable High-Wing Trainer vs. a fast Low-Wing Racer). The app sets aerodynamic constants based on this choice.
    
2.  **Weight Estimation:** You input the mass of your electronics and payload.
    
3.  **Propulsion Setup:** Input your Motor KV, Battery Voltage, and Propeller specs. The app calculates **Pitch Speed**, **Cruise Speed**, and **Static Thrust**.
    
    *   _Safety Check:_ The app warns you if Static Thrust < Total Weight.
        
4.  **Wing Sizing (Auto):** Based on your target cruise speed and weight, the app calculates the required Wing Area () and Root Chord.
    
5.  **Tail Sizing (Auto):** Calculates Horizontal and Vertical stabilizer areas based on tail volume coefficients relevant to your mission profile.
    
6.  **Layout Guide:** The final step provides build instructions, specifically the **Center of Gravity (CG)** location (30% MAC) and wing mounting position.
    

**🧮 The Logic (Under the Hood)**
---------------------------------

The wizard uses standard aerodynamic equations to determine flight worthiness.

**Key Formulas:**

*   **Design Cruise Speed:**
    
*   **Required Wing Area:**
    
*   **Tail Sizing:** Uses Tail Volume Coefficients (!\[\]\[image5\]) specific to the mission type (e.g., for Payload, for Racing).
    

**💻 Installation & Running**
-----------------------------

Choose the method that works best for you:

### **Option A: Quick Install (For Pilots)**

If you just want to use the app on your phone without looking at the code:

1.  Navigate to the **Releases** section of this repository.
    
2.  Download the latest .apk file (e.g., RC\_Wizard\_v1.1.apk).
    
3.  Transfer the file to your Android phone (or download it directly on your phone).
    
4.  Tap the file to install.
    
    *   _Note: You may need to allow installation from "Unknown Sources" in your phone settings._
        

### **Option B: Developer Setup (Source Code)**

If you want to modify the code, contribute, or run it via Android Studio.

#### **1\. Prerequisites**

*   Android Studio (Hedgehog or newer recommended).
    
*   JDK 17 or newer.
    

#### **2\. Get the Code**

**Method 1: Git Clone (Recommended)**

```
git clone https://github.com/paperbukit/RC_Plane_Engineering_Wizard.git
```

**Method 2: Download Archive**

1.  Click the green **Code** button at the top of the repo.
    
2.  Select **Download ZIP** (or download the .tar.gz from Releases).
    
3.  Extract the contents to a folder on your computer.
    

#### **3\. Build & Run**

1.  **Open in Android Studio:**
    
    *   Launch Android Studio.
        
    *   Select "Open" and navigate to the rc-plane-wizard folder (or the extracted folder).
        
2.  **Sync Dependencies:**
    
    *   Wait for Gradle to configure the project.
        
    *   If prompted, click "Sync Now".
        
3.  **Run the App:**
    
    *   **Via USB:** Connect your Android device (ensure USB Debugging is ON).
        
    *   **Via Emulator:** Open Device Manager in Android Studio and create a Virtual Device (AVD).
        
    *   Click the green **Run** (arrow icon) button in the top toolbar.
        

**👥 Acknowledgement**
----------------------

*   **Developed by:** pprbkt (DHANUSH)
    
*   **Formulas:** Based on standard RC aircraft design principles (Wing Cube Loading, Static Thrust calculations).
    
*   **Design Inspiration:** Neo-Brutalism web design trends.
    

**📄 License**
--------------

This project is open-source and available under the **MIT License**.
