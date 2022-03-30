#!/bin/bash

#pyinstaller -F main.py --hidden-import='PIL._tkinter_finder'
gnome-terminal -e "bash -c 'pyinstaller -F main.py --hidden-import='PIL._tkinter_finder';exec $SHELL'"
