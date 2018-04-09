# xCoLab

[![Travis build status](https://img.shields.io/travis/CCI-MIT/XCoLab.svg)](https://travis-ci.org/CCI-MIT/XCoLab)
[![Codacy grade](https://img.shields.io/codacy/grade/c642c48510b04fda9fd2782d92f044cd/master.svg)](https://www.codacy.com/app/MIT-CCI/XCoLab?utm_source=github.com&utm_medium=referral&utm_content=CCI-MIT/XCoLab&utm_campaign=Badge_Grade)
[![StackShare](https://img.shields.io/badge/tech-stack-0690fa.svg)](https://stackshare.io/climate-colab/climate-colab)

The xCoLab is a generic version of the [Climate CoLab](https://climatecolab.org), a platform to crowdsource solutions to climate change.  The xCoLab is intended to be domain independent and appropriate for solving a wide range of complex social problems.

## Getting Started

### Prerequesites

* Java 8
* MySQL 5.6 or higher

### Quick Start

You can set up a copy of the xCoLab like this:

```bash
# Create and initialize database
mysql -u root -p -e 'CREATE DATABASE xcolab CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;'
cat sql/starter/xcolab-schema.sql | mysql -u root -p xcolab
cat sql/starter/xcolab-data.sql | mysql -u root -p xcolab

# Check prerequesites and set up configuration file
./INSTALL.sh

# Update the ~/.xcolab.application.properties file with your database credentials

# Run the xCoLab:
./RUN.sh
```

The components may take a few minutes to start (you can use the `tailAll.sh` script in `scripts/run` to tail the log). After that, the xCoLab will be available at http://localhost:18082.

### Development setup

For a more comprehensive development setup guide, please check out our [setup wiki page](https://github.com/CCI-MIT/XCoLab/wiki/Development-Environment-Setup).

## Architecture

The xCoLab is built with [Spring Boot](https://github.com/spring-projects/spring-boot).
You can read more about our architecture [here](https://github.com/CCI-MIT/XCoLab/wiki/XCoLab-Architecture).

## Contributing

If you notice any issues with the xCoLab, please open an issue in this repository.
If you're interested in contributing to this project, check out [CONTRIBUTING.md](https://github.com/CCI-MIT/XCoLab/blob/master/CONTRIBUTING.md).

## Supporters

We use browserstack for cross-browser testing to ensure the xCoLab looks good across all supported browsers and devices:
[![Browserstack](https://p14.zdusercontent.com/attachment/1015988/HM1p8UVlknjX4qselq9X3PeFq?token=eyJhbGciOiJkaXIiLCJlbmMiOiJBMTI4Q0JDLUhTMjU2In0..cu6DQzoAj0QVYFBeA6evsQ.IdvvylQZiSAV0AZqnnvksOG2G72SFCzFiP8rxwiTHR2kxDcvIZKjhFMnnYh4zLRgEn8vqHa9OpUxcEqv148QNSeRXyS8dsLvXbM48FijM7VTG-cdA8OgxNK2ful5NcH3uWpXEjs0D_2aKbACcXW-raSHZiU87IoUYsNhXbuyGQ4azJXaSox9RfH46-0WFv8ZLb70PYWXIxKRv5yET-_11cjhsE8sITzakVBnQ0SxT-LXN2q4WJsX9sTHqUgu2KTAI7sRu34qAcc9VUGV0-WFpRIPlHUUPavGqDygaqLXAJM.I4GWWPtlv4PIrYDKZQHYiA)](http://browserstack.com/)
