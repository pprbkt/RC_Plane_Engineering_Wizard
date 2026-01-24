# **RC Plane Engineering Wizard ✈️**

**Version:** 1.1

**Status:** Draft / Active Development

The **RC Plane Engineering Wizard** is a modern **Android application** designed to guide hobbyists and engineering students through the complex process of designing a radio-controlled aircraft.

Unlike standard calculators that require you to know your geometry beforehand, this app functions as a sequential "wizard." It starts with your constraints (Mission, Weight, Power) and **automatically calculates** the required airframe geometry (Wing Span, Chord, Tail Areas) based on aerodynamic physics formulas.

## **🚀 Features**

* **Sequential Wizard Flow:** A step-by-step interface that ensures all design requirements are met before moving forward.  
* **Mission-Based Presets:** Automatically sets Lift Coefficients (![][image1]) and Tail Volumes based on your flying style (Trainer, Racing, Aerobatics, or Payload).  
* **Physics-Driven Sizing:** Calculates wing area, root chord, and tail dimensions using real aerodynamic formulas.  
* **Propulsion Analysis:** Specific logic checks to warn if your selected motor/prop combo provides insufficient thrust for your weight.  
* **Assembly Guide:** Generates a custom layout guide telling you exactly where to place the wing and the Center of Gravity (CG).  
* **Beginner Friendly:** Includes a "Technical Hint" system with tooltips explaining complex terms like Motor KV, Prop Pitch, and Wing Loading.

## **🛠️ Tech Stack**

This project is built using modern Android development practices:

* **Language:** Kotlin  
* **UI Framework:** Jetpack Compose (Modern, declarative UI)  
  * **Material 3:** For base components and theming.  
  * **Custom Design System:** "Neo-Brutalism" implementation (Bold borders, high contrast, hard shadows).  
* **Architecture:** MVVM (Model-View-ViewModel)  
  * **ViewModel:** For state management and business logic.  
  * **StateFlow:** For reactive data streams.  
* **Navigation:** Jetpack Navigation Compose  
* **Build System:** Gradle (Kotlin DSL)  
* **Minimum SDK:** 24 (Android 7.0 Nougat)  
* **Target SDK:** 34 (Android 14\)

## **⚙️ How It Works**

The application follows a strict linear flow to ensure engineering validity:

1. **Mission & Configuration:** You define *how* you want to fly (e.g., a stable High-Wing Trainer vs. a fast Low-Wing Racer). The app sets aerodynamic constants based on this choice.  
2. **Weight Estimation:** You input the mass of your electronics and payload.  
3. **Propulsion Setup:** Input your Motor KV, Battery Voltage, and Propeller specs. The app calculates **Pitch Speed**, **Cruise Speed**, and **Static Thrust**.  
   * *Safety Check:* The app warns you if Static Thrust \< Total Weight.  
4. **Wing Sizing (Auto):** Based on your target cruise speed and weight, the app calculates the required Wing Area (![][image2]) and Root Chord.  
5. **Tail Sizing (Auto):** Calculates Horizontal and Vertical stabilizer areas based on tail volume coefficients relevant to your mission profile.  
6. **Layout Guide:** The final step provides build instructions, specifically the **Center of Gravity (CG)** location (30% MAC) and wing mounting position.

## **🧮 The Logic (Under the Hood)**

The wizard uses standard aerodynamic equations to determine flight worthiness.

**Key Formulas:**

* **Design Cruise Speed:** ![][image3]  
* **Required Wing Area:![][image4]**  
* **Tail Sizing:** Uses Tail Volume Coefficients (![][image5]) specific to the mission type (e.g., ![][image6] for Payload, ![][image7] for Racing).

## **💻 Installation & Running**

Follow these steps to build and run the app locally:

1. **Prerequisites:**  
   * Android Studio (Hedgehog or newer recommended).  
   * JDK 17 or newer.  
2. **Clone the Repository:**  
   git clone \[https://github.com/paperbukit/RC\_Plane\_Engineering\_Wizard.git\](https://github.com/paperbukit/RC\_Plane\_Engineering\_Wizard.git)

3. **Open in Android Studio:**  
   * Launch Android Studio.  
   * Select "Open" and navigate to the rc-plane-wizard folder inside the cloned directory.  
4. **Sync Dependencies:**  
   * Wait for Gradle to configure the project.  
   * If prompted, click "Sync Now".  
5. **Run the App:**  
   * Connect an Android device via USB (ensure USB Debugging is on) OR create an Android Virtual Device (AVD) via Device Manager.  
   * Click the **Run** (arrow icon) button in the top toolbar.

## **👥 Acknowledgement**

* **Developed by:** pprbkt (DHANUSH)  
* **Formulas:** Based on standard RC aircraft design principles (Wing Cube Loading, Static Thrust calculations).  
* **Design Inspiration:** Neo-Brutalism web design trends.

## **📄 License**

This project is open-source and available under the **MIT License**.

[image1]: <data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAZCAYAAAArK+5dAAABsElEQVR4Xu2UO0gDQRCGIyoovlAMp3ldLgSDYHeFnZWNnYiFWFkKInaC1qawVWzEVgRbCWgjATsrCx+VhaAGC02lneg3ZC9sRrlo0okf/NztP7Ozc5vdRCL/NEs0Gu12XXfY9/1229fjX0PRDfSAPtALekulUquJRKLT8zyG7qie8yOkQDqd3qTAHc+FTCbTJ342m+3F22eRE563PPv13FBMV5fSsXSq4wHE1yVH+3Vh0pHZjsOw/SU+g561XxdTvJxMJsd0zMYsUNR+KGyPYxY4YNim4zb8LuPkT2g/FApvoRJ7n9GxpqGjDooX0Fkul+vR8TA4cQNm7hW6QNc0OVmTZC5SUSTvNUEFzSySt6R9vHzo15OwV28BuQ8UOUa+7Qc7IE/br4HgHEk38mPrWIDcDXJ2IuoQxOPxBP697X0LSWX0iuYZtga+4zhdNLCGhqz0Km7l4pW0/wW2YIQi5665D2gXnaInul/W+YJ1QAq2H4vFBu2xTUu6wjRFZ93KH1r1azTB9qC87TN3xR43jBxJir+jqcCT7hhv23kNYTqX4iJ5f3Qr2yvvns7/5w/zCSdeaqqq7E+jAAAAAElFTkSuQmCC>

[image2]: <data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABkAAAAZCAYAAADE6YVjAAABxUlEQVR4Xu2UvyvEYRzHTwxCSM5x97373q/FZJDhChkZLDIo/4JSFqvFqGRRl5RBFmWSwQ0GA5PJogykhNgoDLzeeb7fnnu6uqu7hbzr3fP5vJ/P9/N5Ps+PbyTym+D7/gAsplKpxXw+3+nO1w2Sb8BTYxfgO8WW3bi6QNI1+GBsdXSTTqd33Lh60RyPx9tk0MEwRV4psuLENAxNJF+HV5lMxncnGwI6OKBAv6s3DFq553k9shOJhFdxuxC7bT+Xy/W5mnzpmM22rgKgxHnMEjOHvQ0X7Bi1OQOf4ZFWowA+uGX8kJbNZrsYL+AdfIFPyWRyzPp+C345nAoLUHkSoej/3PUvkm8it2gOe1qaDjKI5xb1ol3C6zBJNagi7Y4znsB7EmetuSWzqrB17EH/p+vDQKsZ5sNSLBZrN1IL/j58Y2tGrLh5Fa54sNVgVrwa+HQXw7+Gl9oiI6vwHnyHhSC2Zpgi4WGxbaP4n3AXt8nE6Brp8M90GXRmYYJq0Dn45VulhNqqV/0mrLhlFdYCdG31HoK5qmB/J9y/Jv55sOJAI/EQ2iM81rwdXwv0uMoemB5eNBrtsDWjt1JkQKM7949//FF8A1qrc5zactPpAAAAAElFTkSuQmCC>

[image3]: <data:image/png;base64,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>

[image4]: <data:image/png;base64,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>

[image5]: <data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAZCAYAAAAxFw7TAAABnklEQVR4Xu1TPUsDQRRMSBREG9F4mPvYO1PlxOqwEe0sBNFCCwt/gKkjFv4AexVBCFpoI4Kd2It2XmURUlmk0cJOsFXnebv4fHchAS0z8GAzMzu3L/s2l+vjX6CU2kE1eFUqlQnhuUCdMs8y1znyEOdQMeoT9ez7fi2KogFuAr+OamrPeblcHud6CgjZ0uY2ajJDr6Eu5Yc6wvO8FR34jnUkdfCP4Kcl3xEUQmGoD6wXuUanAr/Nua5AyBQ2vehTrnENrW6USqURznUFbUDQrQ48IU6frIHAXekPw3BQcilg45kOvKLfruuugruzbXvMeNBJCL1Fl/izswNg3NOB9whxsOkBoQsZvir4WcmnAGNdB7ZxkmME7oPOZ/g2u84hgY3OGyqWr8UA2mEu40Mp8JvGz6LUCdQu6hXLArvIuvR9IwgCC+ITqiU1A2rX6NS2Sp7jrzHjKOJ/W3IcZ0YKBtSu0lOAjuZV8lSr0tcTEDCKzbEZGZVMxQ3x0tsT9KU1qVV0MYT1NeoIHziQ3p5AL8eyrGFGFfTQFxjXxx/xBU/eaxVcvf9OAAAAAElFTkSuQmCC>

[image6]: <data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEcAAAAZCAYAAABjNDOYAAADSUlEQVR4Xu1XTWtTQRR9IQqKiojWaL4mH0JoFT+IIoquKqILN6FiwYWIC12IiwrWijvpDzAUBAVFQURwIxIQcaEG/EDBjaGrgIIgKG6EFjdaz+mbSW8vL2nTQiL0HbhMcu6ZeXfumzszz/NChAgRorOIZDKZy8aYm9K0CNx96U+n0xe0pkuI5PP5jYhpH2yzds4HqVRqGw15WKF9EQy6H/YFNgV7DdGgFoG/bf0fYWdgvVrTDTBe2Ce+MFgVse/UmmaIxWKr0KeMPo9p+P0VSdqtdXzIqJ18JSiDWCnv4Nul+W4C8ZxHXIcU9xM2hp8RySssQ78b0E2gLZJAO2znX9NiDjpkndVCobBGuVl6I2wV3zX09PSsRqwvjFrBxq+AWjwe3yB5CSaEiTFiIaDdhP+j8PVrPTscs8nh4LNqF0vtYCKRWC+5bgPx5hDnNx2rTVhjRQSBSbBzvaJ9gUAC9kA8CfuVzWZ3OD6Xy61FVp9J7f8A8faDkjPFly15CfifU4N5nUZ7kfOG/iWsT2unwYcYuym7gdFuxf8PyWRyi9Z7bZYYxkhgvIE2rL9YLC7X4zgwRsa6wOS4eT5FXCvJpWdW4lut97jPwFG1nYYZGDL7EHZO6uDrg2acOsl3Gnj+0cUmx4iy4jiW/yu10+DGBEfFdirDSvzPjU9rwffC6prvJNKLK6s6NZjzWceJDT74pUN813Zi+wbtXq0h4Dtpgo68DkKUgU4OV/8k91DJSxi758BKjpszOSwnm5xXaK95TfYV+MrQ3tF8K2DMIzageRljCLhSNIDnr4PuvTw8COOXxmfucZKXMPa04nwdx6MfXI281DZgZur4j/Y5GL+kfiD4EZHtIa3rBHhQ8CWJjZu3/Qm54l01GHFF4SaM309gdfgzVneC88Z4l1zfWXB13GpVGL+kxvHGYiLbjeXZaeDZ32EPMLlBtLcQ+3FPrHjGBvvNObmTicj4lz6WF5N2z/jXmKtNT0ib0VKr26XxN+tHnn8FP2AH79p3Fr+P8KIOY7Kn2Gr/HIhyQcAG8PGa0s624Oo8Y3d549duhTwC2671Swpp/+LV+G4xft2OIVnXeZPW+iUF1iOXsaCi9psrKrgQIUKECLEA/AOSAhhMSO1eEAAAAABJRU5ErkJggg==>

[image7]: <data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFAAAAAZCAYAAACmRqkJAAAD6ElEQVR4Xu1YS2hTQRRNSBV/qFVrbPN5SRqERsRF/GBRF+JCKVVRoUIWgpt24apFxLW4aFdaBUUsoiBFK4powYULqRuxIFUshVIXFT9g0dKCBRd+zsm7gzfjM02hJKDvwCVv7r1z39wz8+bOJBDw4cOHj/8cjuOchFzRUl9fv9by6YX0KJ8mba8kstnsAoynMZFIrLRtsyAYiURWo28tY9jGcDi8NJ1OL7f1qVRqhW4H+XLIIOQn5AMG0mYHhP4wZFh8btTV1a3R9gohiLG2YDyfIT14HoUMQBK2o41YLLYFbq8kby6I72h3RqPRxcYnHo83S77v6QP7dfyOQaZ0rDxgbBXncUith70NcssmtlLgKsA4n5EAJFpt9HjeA90P7esFyfUbZDvb6HdKdJyMBtGRQBJ2AdKF/LdBHSoIZKDY/ornrG2H/iX0G2x9pWCIgtxBs8romTxJ0KR6QXIthcAnNTU1ywp7e4CkkTwOioPTNtljOrSu0jAJ87PSeuhqIePJZHKT1ttgvvhc06p9jfHw+8h8xnMlMAXnjzILh7SN+0xJQcoI2Y/+SqC9CGYD+kxAJkH8VqMzBILQiOPuk13QZWAKqq4uSBCdhcCr1MnK4+Z52vbPZDILbV0xIE4TXn6kVIF/Y8BroIISCGzWei+AmFXofwD+Ofi/xe8JvcdLoXkOUh1RsWh18r3GpwBmUI67rzDAfugGWOqND2cA9hHoW3/3LD/mg0AN9DkjuffZxxQNxqUfSA3bNgY5K0GegrQo2QeJOz38Gjg7tr6cmG8CmQ/6zUjMNttuYPw884ehXQjkAC4h0LmAx2cEe67S58D4LEUk7nGS0CDB+pBs+kn+/dLmljaEFble9csXW88JMssTMg0ZtG8jBrB1BzyILQbH3aQZu1R5iElaYscxgH2f+PWDxEVGz+oL3XSxCYY9KX3vmb784tB+Z2IqLijHVd/8ez2rfFxV4oA6W2k47jlrAo8hVXjabb9yAO/tcNwbRAvbLABx9zjC8eXB6xjaj6kzK0mK4xAWSMz48Wtj3pAXXDgS63LCLaD5wzOLCdqj8Jk0/QrAjRHGN5AR22YAW87YOcuOe70rOPaUC0wy4VbFGfwehdzH8yfIbu2XcG9ZvfoohvZtyBfITcedCB7K78J3nfGR204f5DW3CvxOQcY89z9BFRz3orxvtA0GCNDtSJXGDO1w3H0jf3KvFLiSMIYcFsCuuVw12Q/5HoQc06vRQoj5cYJA3Oa5xP8DIKwawQY5o2w7btXmflH02uRDIBvrMD9dXnfw/AByEYSet319eIDLl5uyUoXkkO39D4UPHz58+Pin8QvadlSi9ZRuBAAAAABJRU5ErkJggg==>
