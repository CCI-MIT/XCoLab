# Run scripts

These are convenience scripts that can be adapted
to run **supplied binaries**. If you are building
the binaries yourself, you can use the scripts
at the root of this repository without further
configuration (after running `mvn package`).

## Configuration

You will need to configure the directories in
`config.sh` before using the scripts.
`BINARY_SOURCE_DIR` is be the directory
containing the XCoLab binaries you want to deploy
and`DEPLOY_DIR` is the target directory of
the deployment. The jars will be run from the
`DEPLOY_DIR` directory and all configuration
files should reside there.

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