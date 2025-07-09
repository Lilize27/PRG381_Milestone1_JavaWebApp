# PRG381_Milestone1_JavaWebApp

# Project Setup Instructions

## Database Setup

1. Install PostgreSQL and pgAdmin
2. Open pgAdmin and connect to your server
3. Run the setup script:
   - Open Query Tool
   - Open 'sql/setup.sql'
   - Click 'Run'

This will:
- Create the 'WebAppPRG' database
- Create the 'users' table
- Insert a dummy user

## Running the Project

1. Clone this repo
2. Open in NetBeans
3. Update database credentials in RegisterServlet.java and LoginServlet.java if needed
4. Right-click project > Run

Your app will be available at http://localhost:8080/PRG-JavaWebApp
