if (typeof(XCoLab) == 'undefined')
    throw new Error("XCoLab isn't defined");
if (typeof(XCoLab.modeling) == 'undefined')
    throw new Error("XCoLab.modeling isn't defined");

(function () {
    /**
     * A header renderer for modeling widget
     */
    function DefaultHeaderRenderer(modelingWidget) {
        this.modelingWidget = modelingWidget;
        this.render(modelingWidget.container);
        var that = this;

        jQuery(modelingWidget).on("valueChanged", function () {
            that.container.find(".runSimulationButtonHighlight").effect("highlight", {}, 2000);
        });

        jQuery(modelingWidget).on('scenarioFetched', function (event) {
            that.updateNameDescription(event.scenario, true);
        });

        jQuery(modelingWidget).on('modelFetched', function (event) {
            that.updateNameDescription(event.model);
        });
    }


    DefaultHeaderRenderer.prototype = new XCoLab.modeling.BaseXCoLabModelingRenderer();
    DefaultHeaderRenderer.prototype.constructor = DefaultHeaderRenderer.prototype;

    DefaultHeaderRenderer.prototype.containerHtml = "<div class='modelingWidgetHeader'></div>";

    DefaultHeaderRenderer.prototype.renderEdit = function (container) {

        var that = this;
        this.container = container;

        container.append('<a class="model_name_description" target="_blank">' +
            '<h2 class="model_name"></h2></a><p class="model_description"></p>' +
            '<div class="act_left">' +
            '<div class="acthead-l">Actions</div>' +
            '</div> <!-- /act_left -->' +
            '<div class="act_right">' +
            '<div class="acthead-r edit">' +
            'Impacts' +
            '<div class="runmodel"><div class="runSimulationButton_bdr"><a href="javascript:;" class="runSimulationButton">' +
            '<div class="runSimulationButtonHighlight">' +
            '<span>RUN</span> the model' +
            '</div>' +
            '</a></div></div></div></div> <!-- /act_right -->' +
            '<div class="clearfix"></div>' +
            "<div class='actions_wrap'><div class='act_charts-top2'></div></div>");

        container.find(".runmodel").click(function () {
            that.modelingWidget.runTheModel();
        });
    };

    DefaultHeaderRenderer.prototype.renderView = function (container) {
        this.container = container;
        container.append("<a class='model_name_description' target='_blank'>" +
            "<h2 class='model_name'></h2></a>" +
            "<p class='model_description'></p>" +
            "<div class='act_left'><div class='acthead-l'>Actions</div></div>" +
            "<div class='act_right'><div class='acthead-r'>Impacts</div></div>" +
            "<div class='clearfix'></div>" +
            "<div class='actions_wrap'><div class='act_charts-top2'></div></div>");
    };

    DefaultHeaderRenderer.prototype.updateNameDescription = function (model, wasModelRun) {
        if(!model.modelName){
            console.warn("No model name for updateNameDescription", model);
            return;
        }
        this.container.find('.model_name').text(model.modelName);
        this.container.find('.model_description').text(model.modelDescription);

        var isModelRegional = model.modelName.toLowerCase().indexOf("regional") != -1;
        var isModelEMF = model.modelName.toLowerCase().indexOf("emf") != -1;
        var isModelEnRoads = model.modelName.toLowerCase().indexOf("enroads") != -1;
        var modelLink = "";
        var brand = "";
        if (isModelEMF) {
            brand = $("<div class='brand' onclick='location.href=\"https://emf.stanford.edu\"'></div>");
            modelLink = "/resources/-/wiki/Main/EMF27+model+runs";
        } else if (isModelEnRoads) {
            brand = $("<div class='brand' onclick='location.href=\"http://www.climateinteractive.org\"'></div>");
            modelLink = "/resources/-/wiki/Main/EnROADS+by+Climate+Interactive";
        }
        var instructions;

        if (wasModelRun) {
            if (isModelRegional) {
                instructions = "<p class='instructions'>" +
                    "The proposal author(s) have estimated the CO2 emission impacts of this proposal for the country/region through 2100 using " +
                    "regional versions of the " + model.modelName +" modeling tool, specifically adapted for this contest. " +
                    "For more information, see <a href='/wiki/Climate+CoLab+Regional+Modeling+Tools' target='_blank'>Climate CoLab Regional Modeling Tools.</a></p>";
            } else if (isModelEMF) {
                instructions = "<p class='instructions'>" +
                    "The proposal author(s) have estimated the CO2 emission impacts of this proposal for the country/region through 2100 using the " + model.modelName + " modeling tool. " +
                    "For more information, see <a href='/wiki/EMF27+model+runs' target='_blank'>Stanford EMF.</a></p>";
            } else if (isModelEnRoads) {
                instructions = "<p class='instructions'>" +
                    "The proposal author(s) have estimated the CO2 emission impacts of this proposal for the country/region through 2100 using the " + model.modelName + " modeling tool." +
                    "For more information, see <a href='/wiki/EnROADS+by+Climate+Interactive' target='_blank'>Climate Interactive EnROADS.</a></p> ";
            }
        } else {
            if (isModelRegional) {
                instructions = "<p class='instructions'>Regional version " + model.modelName + " modeling tool, specifically adapted for this contest, is available below. Enter inputs that correspond with the actions you propose and click \"Run the Model.\" The tool will project CO2 emissions for the country/region through 2100. " +
                    "For more information, see <a href='/wiki/Climate+CoLab+Regional+Modeling+Tools' target='_blank'>Climate CoLab Regional Modeling Tools.</a></p>";
            } else if (isModelEMF) {
                instructions = "<p class='instructions'>" +
                    "The " + model.modelName + " modeling tool is available below. Enter inputs that correspond with the actions you propose and click \"Run the Model.\" The tool will project CO2 emissions for the country/region through 2100. " +
                    "For more information, see <a href='/wiki/EMF27+model+runs' target='_blank'>Stanford EMF.</a></p>";
            } else if (isModelEnRoads) {
                instructions = "<p class='instructions'>" +
                    "The " + model.modelName + " modeling tool is available below. Enter inputs that correspond with the actions you propose and click \"Run the Model.\" The tool will project CO2 emissions for the country/region through 2100. " +
                    "For more information, see <a href='/wiki/EnROADS+by+Climate+Interactive' target='_blank'>Climate Interactive EnROADS.</a></p> ";
            }
        }

        this.container.find(".model_description .instructions").remove();
        this.container.find(".model_description").append(instructions);

        this.container.find('.model_name_description').attr("href", modelLink);
        this.container.find(".model_description .brand").remove();
        brand.addClass(model.modelName);
        this.container.find(".model_description").prepend(brand);
    }

    XCoLab.modeling.headerRenderers.push(function (modelingWidget) {
        return new DefaultHeaderRenderer(modelingWidget);
    });
}());
