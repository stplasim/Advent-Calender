# Minecraft Advent calendar system

An advent calendar plugin for Minecraft Spigot, Bukkit and Paper Server.
With this plugin you can create an advent calendar on your server. 
You can set an item or command for each day, which can then be picked up by the player.

## How does it work?
The admins of the server can create a calendar and populate it with items and commands. 

The player then only needs to open the calendar with the command `/advent` and get his gift every day. 

The instructions how to create a calendar can be found in the section Setup

## Features

- Fully automatic Advent calendar system.
- Add items easily through drag and drop.
- Give gifts with commands (e.g. In game currency).
- Commands filter. Protects against the execution of destructive commands.
- User-friendly.
- Graphical user interface.

## Commands
> `/advent`
>
> Permission required: **advent.use**
>
> Opens the calendar where the player can open a door every day. 

> `/advent help`
>
> Permission required: **advent.use**
>
> Display the in game help page.

> `/advent info`
>
> Permission required: **advent.use**
>
> Get some information about the plugins

> `/advent admin`
>
> Permission required: **advent.admin**
>
> Get an overview over all day in the calendar.

> `/advent set`
>
> Permission required: **advent.admin**
>
> Create the calendar with the items and command by looking at a chest that contains these items.

> `/advent load`
>
> Permission required: **advent.admin**
>
> Get the chest with the items back. So you can change the calendar anytime. The chest appears at your current position.

## Permissions

> `advent.use`
>
> With this permission the player can open the calendar with the command `/advent`.
> Also the help and info page can be opened.
>
>Please note! Admin commands are not shown to the player.

> `advent.admin`
>
> With this command server admins can open the admin overview and set and load the calendar.

## Setup

Once you have installed the plugin, and you run the `/advent admin` command, you will see a GUI with all fields crossed out. 
If you hover over them, more info will appear. 

To fill the calendar now place an empty chest from the ground.
In this chest you can put the items that you want to be displayed in the calendar later. Note that the position also indicates the day 
(e.g. item in slot 1 is displayed on the first day etc.).

If you want to store a command on a given day you have to use a writable book. In this book you simply write the command you want to have executed. 
Do not save the book. Just put it in the chest.

In case the command requires the player name, use the placeholder `<PLAYER>` instead.
> For example: give <PLAYER> minecraft:diamond

There are two things to keep in mind.
1. The / is not used here.
2. Currently, only commands that the server can execute are supported. If a plugin requires the player to execute it, it will not work. 

To protect against malicious commands, all books with commands are sent through a filter when saved. 
If a book contains a blocked command, the process will be aborted and the player and the blocked command will be displayed in the server console. 

To save the calendar now you only have to look at the box and enter the command `/advent set`. 
If everything went well, you will get a corresponding message.
The chest can now also be removed.

If you want to change something at a later time you can simply bring the chest back.
Just use the command /advent load. A chest will appear at your current position with the items and books used for the commands as you set them.

To save the changes afterwards simply use the set command again.

**Please note!** Currently, the number of days is fixed at 24, even if you put more than 24 items in the box they will be ignored.
This limitation may disappear in the future.

Last but not least you can get an overview over everything in the admin menu.

## Contribution
You want to help? You can do this easily by either creating an issue with the Label feature. 
I'll see what I can do.
Otherwise, you can simply fork the repository and open a pull request afterwards.

## License
This project is licensed under the MIT license. More information in the LICENSE file
