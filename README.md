# InjectorPatch
Created to detect & prevent the loading of a malware loader going around in the Minecraft community. Classes that are flagged are emptied & not loaded onto the heap. 

# Setup
1. System Requirements - You will need access to the command line args on your host. If you are using a shared host, your on your own...
2. Add the `SupremeInjectionProtector.jar` to your MAIN server directory (the same place as your spigot.jar)
3. Add `-javaagent:SupremeInjectionProtector.jar` to your command line args on your panel.
4. Restart, and your done.


# Flagging
```
[03:16:00 INFO]: Blocked class loading for me/lucko/luckperms/bukkit/loader/BukkitLoaderPluginL10 - Class flagged as injected.
[03:16:00 INFO]: Source: /home/container/plugins/LuckPerms-Bukkit-5.3.0.jar
```
