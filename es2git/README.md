# GIT-Working with Remotes
- I repository remoti sono versioni del tuo progetto che sono ospite su Internet
- La collaborazione con altri programmatori Implica la gestione di questi repository remoti e il push e il pull di dati

Fondamentalmente è saper **sincronizzare il nostro lavoro locale con un repository remoto.**

*comandi principali:*

`git remote -v `--> visualizza i server remoti collegati al nostro repository
## Aggiungere o rimuovere un repository remoto
` git remote add <nome> <url> `
## Caricare il lavoro locale sul repository remoto
` git push <remote> <ramo-locale> `
## Aggiornare la copia locale del rpository, allineandola con la versione remota
` git pull <rmote> <ramo-locale> `
