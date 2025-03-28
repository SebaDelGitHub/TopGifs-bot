# GIF Data Storage Information

## JSON File Structure
The GIFs are stored in JSON files that contain the following structure:

- **Server Name**: The name of the Discord server.
- **Server ID**: The unique identifier of the server.
- **GIFs**: A collection of GIF URLs and their usage count.

### Example JSON File
```json
{
  "serverName": "Your Server",
  "serverId": "99999999999999999",
  "gifs": {
    "https://tenor.com/view/example1": 5,
    "https://tenor.com/view/example2": 1,
    "https://tenor.com/view/example3": 2,
    "https://tenor.com/view/example4": 2
  }
}
```

## Default Storage Location
By default, the JSON files are stored in the following directory:
```
src/main/java/data
```
However, you can change this location by modifying the **environment variables**.

## Customization
- The storage path can be customized through **environment variables**.
- The file name is based on the **server ID** (e.g., `serverId_gif_data.json`).
- The GIFs are stored as **URL keys with their respective usage count**.
