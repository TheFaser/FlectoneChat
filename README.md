![about](https://media.discordapp.net/attachments/570557439888588821/813722970332725288/about.jpg)

When creating an almost vanilla server, I noticed that there was no convenient and easy-to-use chat plugin. There are quite a few plugins, but there is not what exactly is needed together, so I present to you the FlectoneChat plugin


![Features](https://media.discordapp.net/attachments/570557439888588821/813722971008925696/features.jpg)
 
- **Join**/**left** the game message
- Ability to issue **Gamemode** and **OP** when join the game
- Communication with the player through private messages
   - **/tell**, **/msg**, **/send**, **/m**, **/w** and **/reply**, **/r**
- Local and global chat that depends only on you
- Ignoring the player, his actions and list of ignored
   - **/ignore** and **/ignorelist**
- Replaced **/me** and **/try** to test your luck
- Reload the plugin config with one command
   - **/flectone reload**

## Give


![Config](https://media.discordapp.net/attachments/570557439888588821/813722982329221150/config.jpg)

- Choose plugin language
- Change the gamemode on login
  - ```gamemode: "SURVIVAL"```
- Change the chat and command prefix
  - ```prefix: "[L]"```
- Change the range for local chat and conditions for global chat
  - ```range: 100```
  - ```condition: "!"```
- Customize your sound

![sound](https://media.discordapp.net/attachments/570557439888588821/813723040386383872/sound.jpg)

- You can false/true sound. Also, specify a separate sound for each or not change anything.
```  
action:  
  enable: true
  success: "minecraft:block.note_block.iron_xylophone"
  fail: "minecraft:block.note_block.bass"
```

- Enable - true or false
```
enable: true
enable: false[/code]
````

- Success - sound when successful. Fail - on error
``` 
success: "minecraft:block.note_block.iron_xylophone"
fail: "minecraft:block.note_block.bass"
```
If you are going to change the sound itself, take it from the command /playsound in minecraft
