# Run scripts

These are convenience scripts that can be adapted
to run **compiled binaries**. If you are building
the binaries yourself, make sure you run 
`./copyBinaries` after running `mvn package`.

## Configuration

If you are not building from source and using the
default directories, you will need to configure
the directories in `config.sh` before using these
scripts. If you use the standard directories for
the binaries and the bundle, you just need to set
the `ROOT_DIR` variable to the directory containing
the xCoLab installation.

If you want to customize the binary or bundle locations,
you can configure the derived variables as follows:
`BINARY_SOURCE_DIR` is be the directory
containing the XCoLab binaries you want to deploy
and `DEPLOY_DIR` is the target directory of
the deployment. The jars will be run from the
`DEPLOY_DIR` directory and all configuration
files should reside there.

You can also optionally configure the `LOG_SERVER` variable.
If the variable is set, logs will be copied to the destination
during each restart, before the original is deleted. 

## Running the scripts

To start the XCoLab, simply run:

```bash
./startAll.sh
```

To start individual components, run the
`startEureka.sh`, `startServices.sh`,
or `startView.sh` scripts.

To stop the XCoLab, run:

```bash
./stopAll.sh
```

To stop individual components, run the 
`stopEureka.sh`, `stopServices.sh`,
or `stopView.sh` scripts.

To start or stop individual services, run the
`startServices.sh` or `stopServices,sh` with the
service name as argument:

```
# Start the content service
./startServices.sh content-service

# Stop the content service
./stopServices.sh content-service
```

If you are compiling from source, make sure to run
`./copyBinaries.sh` after each re-compilation and
before starting the servers.
