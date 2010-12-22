RandomStartup
=============

A very simple OSGi bundle that provides a Felix Shell command to start all non-active bundles in a random, but reproducible manner. I use it to test the robustness of OSGi-based applications, in particular to root out startup-dependencies between bundles.

Installation
------------
After installation, a new [Apache Felix Shell](http://felix.apache.org/site/apache-felix-shell.html) command will be available, `startrandom`.

Usage
-----

    startrandom <id>
    
will start all bundles which are currently not `ACTIVE` in a random order. The `<id>` you pass in is the seed for the randomizer, so, for a framework in the same state (same bundles, installed with same ids, and the same bundles active), you can reproduce the startup order.
    

The project
-----------
The project is an Eclipse project based on [Bndtools](https://github.com/njbartlett/bndtools); you should be able to import it into an  Eclipse with the Bndtools plugin installed, and start building right away.