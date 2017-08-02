<?php

/** @noinspection PhpUndefinedVariableInspection */
$logoPath = $config['logoPath'];
$colorPrimary = $config['colorPrimary'];
$colorPrimaryHover = $config['colorPrimaryHover'];
$colorBackground = $config['colorBackground'];

?>

<style>
    * {
        box-sizing: border-box;
    }

    body {
        background-color: <?= $colorBackground ?>;
    }

    .text-center {
        text-align: center;
    }

    h2 {
        font-size: 27px;
        font-weight: 700;
    }

    .contestbox {
        width: 452px;
        margin: 10px auto;
        padding: 20px;

        color: #575757;
        font-family: "proxima-nova", "proxima-nova-1", Arial, Helvetica, sans-serif;
        font-size: 19px;
        line-height: 27px;

        border: 1px solid #bababa;
        border-radius: 8px;

        background: #fff;
    }

    input {
        display: block;
        width: 100%;
        height: 34px;
        padding: 6px 12px;
        margin-bottom: 7px;

        color: #888888;
        font-size: 14px;
        font-weight: 200;
        line-height: 1.42857143;

        border: 1px solid #bababa;
        border-radius: 4px;

        background-color: #fff;
        background-image: none;
        box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
    }

    .c-Button__primary {
        display: inline-block;
        padding: 8px;
        cursor: pointer;

        color: #fff;
        font-size: 16px;
        font-weight: 700;
        line-height: normal;
        text-decoration: none;

        border: 1px solid <?= $colorPrimaryHover ?>;
        border-radius: 5px;

        background-color: <?= $colorPrimary ?>;
    }

    .c-Button__primary.block {
        width: 100%;
    }

    .c-Button__primary:hover {
        color: #fff;
        text-decoration: none;
        background-color: <?= $colorPrimaryHover ?>;
    }

</style>
