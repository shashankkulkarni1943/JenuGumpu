<div align="center">

# 🍯 ಜೇನು ಗುಂಪು — Jenu Gumpu
### Honey Producer's Collective App

<p align="center">
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" />
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Android_Studio-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white" />
  <img src="https://img.shields.io/badge/Room_DB-003B57?style=for-the-badge&logo=sqlite&logoColor=white" />
  <img src="https://img.shields.io/badge/Material_UI-757575?style=for-the-badge&logo=material-design&logoColor=white" />
  <img src="https://img.shields.io/badge/MindMatrix-VTU_Internship-FF6B35?style=for-the-badge" />
</p>

<p align="center">
  <b>Empowering tribal honey hunters of Karnataka to become brands — not just suppliers.</b><br/>
  A GenAI-assisted Android app that helps rural honey producers grade, track, price, and collectively negotiate their honey harvest.
</p>

</div>

---

## 📌 The Problem

> Tribal and rural honey hunters in districts like Hassan, Chikmagalur, and Sakleshpur sell raw honey to middlemen at **₹100–120/kg** — while the same honey retails in Bengaluru for **₹350–480/kg**.

They lack:
- Knowledge of honey grading (moisture, colour, purity)
- Access to retail price data
- Tools to track batches and negotiate collectively
- A platform to form producer groups

**Jenu Gumpu solves all of this — offline, in Kannada, on any Android phone.**

---

## 🏗️ Architecture

### High-Level App Architecture

```mermaid
graph TD
    A[Login Screen<br/>User Authentication] --> B[MainActivity<br/>Bottom Navigation]
    B --> C[Harvest Log<br/>HarvestFragment]
    B --> D[Grading Tool<br/>GradingFragment]
    B --> E[Price Monitor<br/>PriceFragment]
    B --> F[Collective Stock<br/>CollectiveFragment]
    B --> G[Batch Tracker<br/>TrackerFragment]

    C --> H[HarvestViewModel]
    D --> H
    E --> H
    F --> H
    G --> H

    H --> I[HarvestRepository]
    I --> J[(Room Database<br/>harvest_entries)]
    J --> K[HarvestDao<br/>CRUD Operations]
```

### MVVM Data Flow

```mermaid
graph LR
    UI[UI Layer<br/>Fragments + Adapters] -->|User Action| VM[ViewModel<br/>LiveData + Logic]
    VM -->|Observe| UI
    VM -->|Call| REPO[Repository]
    REPO -->|Query| DB[(Room DB)]
    DB -->|Return LiveData| REPO
    REPO -->|Post Value| VM
```

---

## 🔄 App User Flow

```mermaid
flowchart TD
    START([App Launch]) --> LOGIN[Login Screen]
    LOGIN -->|Valid Credentials| HOME[Home Dashboard]
    LOGIN -->|Invalid| ERR[Show Error]
    ERR --> LOGIN

    HOME --> H[📋 Log Harvest]
    HOME --> G[⭐ Grade Honey]
    HOME --> P[💰 Check Prices]
    HOME --> C[👥 Collective Stock]
    HOME --> T[📦 Batch Tracker]

    H --> H1[Enter Qty, Location, Moisture]
    H1 --> H2[Select Floral Source]
    H2 --> H3[Auto-assign Batch ID]
    H3 --> H4[Auto-grade A / B / C]
    H4 --> DB[(Room Database)]

    G --> G1[Select Honey Colour]
    G1 --> G2[Set Moisture via Slider]
    G2 --> G3{Moisture Check}
    G3 -->|< 18%| GA[Grade A ✅ Retail Ready]
    G3 -->|18–20%| GB[Grade B ⚠ Filter First]
    G3 -->|> 20%| GC[Grade C ❌ High Risk]

    P --> P1[Select Floral Source]
    P1 --> P2[View Retail vs Wholesale vs Middleman]
    P2 --> P3[Enter Qty + Packaging Cost]
    P3 --> P4[See Profit Calculation]

    C --> C1[View All Member Stock]
    C1 --> C2[Total Collective kg]
    C2 --> C3[Negotiate with Retailers]

    T --> T1[View All Batches]
    T1 --> T2[Mark: Filtered / Labelled / Listed]
```

---

## 🍯 Honey Grading Algorithm

```mermaid
flowchart LR
    INPUT([Moisture % Input]) --> CHECK1{Moisture < 18%?}
    CHECK1 -->|Yes| A[🌟 GRADE A<br/>Retail Ready<br/>Premium Price]
    CHECK1 -->|No| CHECK2{Moisture 18–20%?}
    CHECK2 -->|Yes| B[🔶 GRADE B<br/>Filter Before Selling<br/>Wholesale Price]
    CHECK2 -->|No| C[⚠️ GRADE C<br/>High Fermentation Risk<br/>Do Not Sell]

    A --> PRICE_A[₹350–480/kg Retail]
    B --> PRICE_B[₹200–300/kg Wholesale]
    C --> PRICE_C[Value-Added Products Only]
```

---

## 💰 Profit Calculator Logic

```mermaid
flowchart TD
    IN1[Quantity kg] --> CALC
    IN2[Floral Source] --> PRICES[Price Lookup Table]
    IN3[Packaging Cost ₹/kg] --> CALC
    PRICES --> RETAIL[Retail Price]
    PRICES --> MIDDLE[Middleman Price]

    CALC[Profit Calculation Engine] --> OUT1[Profit via Collective<br/>qty × retail - cost]
    CALC --> OUT2[Loss via Middleman<br/>qty × middleman - cost]
    OUT1 --> DIFF[💡 Difference = Extra Earnings<br/>by bypassing middleman]
```

---

## ✨ Core Features

### 🔐 Login Screen
- Secure entry point with username & password authentication
- Clean Material UI with the Jenu Gumpu branding
- Session management to keep users logged in

### 📋 Harvest Log
- Record **date, location, quantity (kg), moisture %** and **floral source**
- Floral sources: Coffee Blossom, Wildflower, Jamun, Eucalyptus, Neem
- **Auto Batch ID** generated on every entry (e.g. `BATCH-001`)
- **Auto Grading** — Grade A/B/C assigned based on moisture %
- All data persisted locally using **Room Database**

### ⭐ Grading Tool
- **Visual Colour Guide** — Golden, Amber, Dark Brown with descriptions
- **Interactive Moisture Slider** — drag to your refractometer reading
- **Real-time Grade Result** with actionable advice
- Grading Key card for quick reference

### 💰 Price Monitor + Profit Calculator
- Real-time retail vs wholesale vs middleman price comparison
- Price data for 5 floral varieties
- **Profit Calculator** — enter your qty and packaging cost to see exact earnings
- Shows how much more you earn by **bypassing the middleman**

### 👥 Collective Stock
- Aggregates stock from all group members
- Shows **total collective kg** — a key negotiation tool with retailers
- Member-wise breakdown with location

### 📦 Batch Tracker
- Lists all logged batches with full details
- Checkbox-based status tracking: **Filtered → Labelled → Listed**
- Traceability from forest to retail shelf

---

## 🗄️ Database Schema

```mermaid
erDiagram
    HARVEST_ENTRIES {
        int id PK
        string batchId
        float quantityKg
        string location
        string floralSource
        string harvestDate
        string grade
        float moisture
        boolean isFiltered
        boolean isLabelled
        boolean isListed
        long createdAt
    }
```

---

## 🚀 Running Locally — Step by Step

### Prerequisites
- [Android Studio Hedgehog 2023.1.1+](https://developer.android.com/studio)
- JDK 17
- Android device or emulator running **Android 7.0 (API 24)+**

### Step 1 — Clone the Repository
```bash
git clone https://github.com/YOUR_USERNAME/JenuGumpu.git
```

### Step 2 — Open in Android Studio
1. Open **Android Studio** → **File → Open**
2. Select the `JenuGumpu` folder
3. Wait for Gradle sync to complete

### Step 3 — Configure gradle.properties
Make sure the file `gradle.properties` at the root contains:
```properties
android.useAndroidX=true
android.enableJetifier=true
```

### Step 4 — Build and Run
1. Connect your Android device via USB and enable **USB Debugging**
   - OR create an emulator: **Pixel 4a, API 30, x86**
2. Click **▶ Run** or press `Shift + F10`
3. Grant any requested permissions

---

## 📁 Project Structure

```
JenuGumpu/
├── app/src/main/
│   ├── java/com/jenugumpu/
│   │   ├── ui/
│   │   │   ├── MainActivity.java
│   │   │   ├── harvest/
│   │   │   │   ├── HarvestFragment.java
│   │   │   │   ├── HarvestViewModel.java
│   │   │   │   └── HarvestAdapter.java
│   │   │   ├── grading/
│   │   │   │   └── GradingFragment.java
│   │   │   ├── price/
│   │   │   │   └── PriceFragment.java
│   │   │   ├── collective/
│   │   │   │   └── CollectiveFragment.java
│   │   │   └── tracker/
│   │   │       ├── TrackerFragment.java
│   │   │       └── TrackerAdapter.java
│   │   └── data/
│   │       ├── model/
│   │       │   └── HarvestEntry.java
│   │       ├── db/
│   │       │   ├── AppDatabase.java
│   │       │   └── HarvestDao.java
│   │       └── repository/
│   │           └── HarvestRepository.java
│   └── res/
│       ├── layout/          # All XML layouts
│       ├── drawable/        # Custom shapes & icons
│       ├── values/          # Colors, strings, themes
│       └── menu/            # Bottom navigation menu
```

---

## 🎯 Impact Goals

| Goal | Description |
|------|-------------|
| 🌿 **Tribal Empowerment** | Improving livelihoods of forest-dwelling communities in Hassan & Chikmagalur districts |
| 🍃 **Organic Growth** | Promoting "Forest-to-Table" chemical-free products |
| 🐝 **Sustainable Harvest** | Guidelines on harvesting honey without killing the bee colony |
| 💸 **Fair Pricing** | Helping producers earn 3–4× more by bypassing middlemen |

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Java |
| UI | XML Layouts + Material Components |
| Architecture | MVVM (ViewModel + LiveData) |
| Database | Room (SQLite) |
| Navigation | Fragment + BottomNavigationView |
| Build | Gradle 8.x + AGP |
| Min SDK | Android 7.0 (API 24) |
| Target SDK | Android 14 (API 34) |

---

## 📜 License

This project was developed as part of the **MindMatrix VTU Internship Program**.

---

<div align="center">
  <b>Built with ❤️ for the honey hunters of Karnataka</b><br/>
  <i>"From the forest floor to the retail shelf — with dignity and fair price."</i>
</div>
