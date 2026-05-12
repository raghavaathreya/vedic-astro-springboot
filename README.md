# Vedic Astrology Calculator - Spring Boot Backend

Full-stack Vedic astrology application with RESTful API and AI-powered personalized insights.

## 🌟 Features

- **Birth Chart Generation**: Complete Kundli/horoscope calculation with planetary positions
- **Rashi & Nakshatra Analysis**: Moon sign and birth star determination  
- **Lagna Calculation**: Ascendant sign with precise degree positioning
- **AI-Powered Insights**: Personalized horoscope analysis using Groq AI (Llama 3.3 70B)
- **Dasha Periods**: Vimshottari Mahadasha and Antardasha calculations
- **Current Transits**: Real-time planetary transit analysis
- **Daily Predictions**: Personalized daily guidance based on birth chart
- **Horoscope Summary**: Comprehensive life analysis including career, relationships, health
- **Cross-Platform Frontend**: React Native web app deployed on Netlify

## 🛠️ Tech Stack

**Backend:**
- Java 21, Spring Boot 3.x
- REST API architecture
- Groq AI (Llama 3.3 70B)
- Maven

**Frontend:**
- React Native with TypeScript
- Expo for cross-platform support
- React Navigation (6 bottom tabs)
- React Native Paper components

**Python Microservice:**
- Flask
- Swiss Ephemeris for astronomical calculations
- Deployed on Render

**Deployment:**
- Spring Boot → Railway
- Python → Render  
- Frontend → Netlify
- **Total Cost: $0/month**

## 🏗️ Architecture

```
Client (Web/Mobile)
    ↓
Frontend (React Native - Netlify)
    ↓
Spring Boot API (Railway)
    ↓
├─→ Python Service (Render) → Swiss Ephemeris → Astronomical Calculations
    ↓
├─→ GroqService → AI Insights Generation
    ↓
JSON Response
```

## 📡 API Endpoints

### Calculate Kundli
```http
POST /api/kundli/calculate
Content-Type: application/json

{
  "year": 2000,
  "month": 5,
  "day": 13,
  "hour": 20,
  "minute": 30,
  "latitude": 12.9716,
  "longitude": 77.5946
}
```

**Response:**
```json
{
  "success": true,
  "rashi": "Kanya (Virgo)",
  "nakshatra": "Uttara Phalguni (Pada 3)",
  "lagna": "Vrishchika (Scorpio)",
  "ascendantDegree": 234.82,
  "planets": { ... },
  "aiInsights": { ... },
  "currentTransits": { ... },
  "mahadashas": [ ... ],
  "dailyPredictions": { ... },
  "horoscopeSummary": { ... }
}
```

### Health Check
```http
GET /api/kundli/health
```

## ⚙️ Environment Variables

```properties
PORT=8080
GROQ_API_KEY=your_groq_api_key_here
PYTHON_API_URL=https://vedic-astro-python.onrender.com/calculate
```

## 🚀 Local Development

### Prerequisites
- Java 21 or higher
- Maven 3.6+
- Node.js 16+ (for frontend)
- Python backend running

### Backend Setup

1. **Clone the repository**
```bash
git clone https://github.com/raghavaathreya/vedic-astro-springboot.git
cd vedic-astro-springboot
```

2. **Configure environment variables**
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

Edit `application.properties`:
```properties
server.port=8080
groq.api.key=YOUR_GROQ_API_KEY
python.api.url=http://localhost:5000/calculate
```

3. **Build and run**
```bash
./mvnw clean install
./mvnw spring-boot:run
```

4. **Test the API**
```bash
curl -X POST http://localhost:8080/api/kundli/calculate \
  -H "Content-Type: application/json" \
  -d '{
    "year": 2000,
    "month": 5,
    "day": 13,
    "hour": 20,
    "minute": 30,
    "latitude": 12.9716,
    "longitude": 77.5946
  }'
```

### Frontend Setup

Frontend is built with React Native and Expo.

```bash
# Navigate to frontend directory
cd path/to/astrology-app

# Install dependencies
npm install

# Start development server
npx expo start

# For web
npx expo start --web
```

## 🌐 Deployment

### Backend (Railway)
- **URL**: `https://vedic-astro-springboot-production.up.railway.app`
- Auto-deploys from GitHub
- Environment variables set in Railway dashboard

### Python Service (Render)
- **URL**: `https://vedic-astro-python.onrender.com`
- Auto-deploys from GitHub
- Free tier (sleeps after 15 min inactivity)

### Frontend (Netlify)
- **Live URL**: [https://vedic-astro-app.netlify.app/]
- Build command: `npx expo export --platform web`
- Deploy: Drag `dist/` folder to Netlify

## 📁 Project Structure

```
Spring Boot Backend:
src/
├── main/
│   ├── java/com/example/demo/
│   │   ├── controller/
│   │   │   └── AstrologyController.java
│   │   ├── service/
│   │   │   ├── AstrologyService.java
│   │   │   └── GroqService.java
│   │   ├── model/
│   │   │   ├── BirthDetails.java
│   │   │   └── KundliResponse.java
│   │   └── DemoApplication.java
│   └── resources/
│       └── application.properties

Frontend:
app/
├── (tabs)/
│   └── index.tsx                  # Birth details form
├── kundli.tsx                     # Tab navigator
├── kundli-tabs/
│   ├── OverviewTab.tsx           # Chart & positions
│   ├── RulingPlanetsTab.tsx      # Planetary rulers
│   ├── TodayTab.tsx              # Daily predictions
│   ├── HoroscopeTab.tsx          # AI insights
│   ├── TransitsTab.tsx           # Current transits
│   └── DashaTab.tsx              # Dasha periods
└── components/
    ├── NorthIndianChart.tsx
    ├── SouthIndianChart.tsx
    └── ... (other components)
```

## 🎨 Frontend Features

- **6 Navigation Tabs**: Overview, Ruling Planets, Today, Horoscope, Transits, Dasha
- **Dual Chart Styles**: North Indian (diamond) and South Indian (grid)
- **Responsive Design**: Works on web and mobile
- **Dark Theme**: Mystical dark background with gold accents
- **Platform-Specific Pickers**: Native date/time inputs for web and mobile
- **Location Autocomplete**: Google Places-style location search

## 🔗 Related Repository

- **Python Backend**: [vedic-astro-backend](https://github.com/raghavaathreya/vedic-astro-backend)

## 🌐 Live Demo

🔗 **Website**: [https://vedic-astro-app.netlify.app/]

Try it:
1. Enter your birth details (date, time, location)
2. Click "Generate My Kundli"
3. Explore all 6 tabs with personalized insights

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📄 License

This project is open source and available under the MIT License.

## 👤 Author

**Raghavendra G V**
- GitHub: [@raghavaathreya](https://github.com/raghavaathreya)


## 🙏 Acknowledgments

- Swiss Ephemeris for astronomical calculations
- Groq for AI-powered insights (Llama 3.3 70B)
- Spring Boot framework
- React Native and Expo teams
- Railway, Render, and Netlify for hosting
