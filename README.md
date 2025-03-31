# TopGifs Bot

This is a simple Discord bot that tracks GIFs from **Tenor** and **Giphy**. It stores the usage data in JSON files located in `src/main/java/data/`.

## Installation

1. Clone this repository:
   ```sh
   git clone https://github.com/SebaDelGitHub/TopGifs-bot.git
   cd .\TopGifs-bot\
   ```

2. Create a `.env` file in the root (`src/`) directory with the following content:

   ```sh
   # .env-example

   # Discord bot token
   DISCORD_BOT_TOKEN=your_discord_bot_token_here

   # Path to the JSON file where GIF data will be saved
   JSON_FILE_PATH=src/main/java/data/
   ```

3. Install dependencies and run the bot.

## Usage

- The bot listens for messages containing GIFs and records them.
- You can view the top 10 most used GIFs by using the command `$topgifs`.

## Notes

- JSON files are stored in `src/main/java/data/`.
- This bot requires **Java** to run.
