# MCGameSettings

A simple Minecraft plugin for controlling basic game/world settings at command.

commands:

     mcgs:
        description: Info on the MCGameSettings plugin & version number.
        usage: /<command> <on/off/silentDeath/opReq>
     allowSetting:
        description: Toggle player permission to use the MCGS commands.
        usage: /<command> <player>
        aliases: [allowMCGS]
     day:
        description: MCGS / Set the time to day (6000 ticks).
        usage: /<command>       
     sunrise:
        description: MCGS / Set the time to sunrise (23000 ticks).
        usage: /<command>
     dawn:
        description: MCGS / Set the time to dawn (0 ticks).
        usage: /<command>
     morning:
        description: MCGS / Set the time to morning (1000 ticks).
        usage: /<command>
     dusk:
        description: MCGS / Set the time to dusk (12000 ticks).
        usage: /<command>
     sunset:
        description: MCGS / Set the time to sunset (12500 ticks).
        usage: /<command>
     night:
        description: MCGS / Set the time to night (13500 ticks).
        usage: /<command>    
     midnight:
        description: MCGS / Set the time to midnight (18000 ticks).
        usage: /<command>
     rain:
        description: MCGS / Set the weather to rain or snow!
        usage: /<command>
        aliases: [raining, snow, snowing]
     storm:
        description: MCGS / Set the weather to a thunderstorm!
        usage: /<command>
        aliases: [lightning, thunder, thunderstorm]
     sunny:
        description: MCGS / Set the weather to clear skies!
        usage: /<command>
     creative:
        description: MCGS / Set your gamemode to Creative Mode.
        usage: /<command>
        aliases: [god, godmode]
     survival:
        description: MCGS / Set your gamemode to Survival Mode.
        usage: /<command>
     adventure:
        description: MCGS / Set your gamemode to Adventure Mode.
        usage: /<command>
     spectator:
        description: MCGS / Set your gamemode to Spectator Mode.
        usage: /<command>
