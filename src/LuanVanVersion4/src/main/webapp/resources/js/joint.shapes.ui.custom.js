joint.shapes.ui = {}

joint.shapes.ui.Form = joint.shapes.basic.Generic.extend({
	markup: [
        '<g class="scalable">',
            '<rect/>',
        '</g>'
    ].join(''),

	defaults: joint.util.deepSupplement({ 
        type: 'ui.Form',
        nameClass: "",
        parent: "",
        size: {
        	width: 200, height: 200	
        },
        attrs: {
            rect: { width: 200, height: 200, rx: 0, ry: 0, 'stroke-width': 2, stroke: '#000000', fill: '#ffffff'}
        }
    }, joint.shapes.basic.Generic.prototype.defaults)
});

joint.shapes.ui.FormView = joint.dia.ElementView.extend({

    template : [
        '<div class="ui-form">',
            '<button class="delete">x</button>',
            '<div class="resize"><i class="glyphicon glyphicon-fullscreen"></i></div>',
            '<button class="copy">+</button>',
        '</div>'
    ].join(''),

    initialize: function() {
        _.bindAll(this, 'updateFormView');
        joint.dia.ElementView.prototype.initialize.apply(this, arguments);

        this.$box = $(_.template(this.template)());
        this.$box.find('.delete').on('mousedown', _.bind(this.model.remove, this.model));
        
        // Update the box position whenever the underlying model changes.
        this.model.on('change', this.updateFormView, this);
        // Remove the box when the model gets removed from the graph.
        this.model.on('remove', this.removeFormView, this);

        this.updateFormView();
    },
    render: function() {
        joint.dia.ElementView.prototype.render.apply(this, arguments);
        this.paper.$el.prepend(this.$box);
        this.updateFormView();
        return this;
    },
    updateFormView: function() {
        // Set the position and dimension of the box so that it covers the JointJS element.
        var bbox = this.model.getBBox();
        // Example of updating the HTML with a data stored in the cell model.
        this.$box.css({
            width: bbox.width,
            height: bbox.height,
            left: bbox.x,
            top: bbox.y,
            transform: 'rotate(' + (this.model.get('angle') || 0) + 'deg)'
        });
    },
    removeFormView: function() {
        this.$box.remove();
    }
});

joint.shapes.ui.Button = joint.shapes.basic.Generic.extend({
	 markup: [
        '<g class="scalable">',
            '<rect/>',
        '</g>',
        '<text class="name">Button</text>'
    ].join(''),

    defaults: joint.util.deepSupplement({
        type: 'ui.Button',
        nameClass: "btn btn-default ",
        size: { width: 100, height: 30 },
        name: 'Button',
        parent: "",
        attrs: {
            rect: { width: 100, height: 30, fill: "#eeeeee", stroke: "#000000", 'stroke-dasharray': "none", 'stroke-width': 2 },
            text: { x: 50, y: 20, fill: '#000000', 'text-anchor': 'middle', 'font-family': 'Arial', 'font-size': 16 }
        },
        connect: []
    }, joint.shapes.basic.Generic.prototype.defaults)
});

joint.shapes.ui.ButtonView = joint.dia.ElementView.extend({

    template : [
        '<div class="ui-button">',
            '<button class="delete">x</button>',
            '<input class="name" type="text"/>',
            '<div class="resize"><i class="glyphicon glyphicon-fullscreen"></i></div>',
            '<button class="copy">+</button>',
            '<button class="link"><i class="glyphicon glyphicon-link"></i>sdfdf</button>',
        '</div>'
    ].join(''),

    initialize: function() {
        _.bindAll(this, 'updateButtonView');
        joint.dia.ElementView.prototype.initialize.apply(this, arguments);
        this.$box = $(_.template(this.template)());
        // Prevent paper from handling pointerdown.
        this.$box.find('input').on('mousedown click', function(evt) { evt.stopPropagation(); });
        // This is an example of reacting on the input change and storing the input data in the cell model.
        this.$box.find('input').on('change', _.bind(function(evt) {
            this.model.set('name', $(evt.target).val());
            this.model.attr('text/html', $(evt.target).val());
            $(evt.target).val("");
        }, this));
        this.$box.find('input').on('focus', _.bind(function(evt) {
            this.model.attr('text/fill-opacity', 0);
        }, this));
        this.$box.find('input').on('focusout', _.bind(function(evt) {
            this.model.attr('text/fill-opacity', 1);
        }, this));
        this.$box.find('.delete').on('mousedown', _.bind(this.model.remove, this.model));
        // Update the box position whenever the underlying model changes.
        this.model.on('change', this.updateButtonView, this);
        // Remove the box when the model gets removed from the graph.
        this.model.on('remove', this.removeButtonView, this);

        this.updateButtonView();
    },
    render: function() {
        joint.dia.ElementView.prototype.render.apply(this, arguments);
        this.paper.$el.prepend(this.$box);
        this.updateButtonView();
        return this;
    },
    updateButtonView: function() {
        // Set the position and dimension of the box so that it covers the JointJS element.
        var bbox = this.model.getBBox();
        // Example of updating the HTML with a data stored in the cell model.
        this.$box.css({
            width: bbox.width,
            height: bbox.height,
            left: bbox.x,
            top: bbox.y,
            transform: 'rotate(' + (this.model.get('angle') || 0) + 'deg)'
        });
    },
    removeButtonView: function() {
        this.$box.remove();
    }
});

joint.shapes.ui.Div = joint.shapes.basic.Generic.extend({
	markup: [
        '<g class="scalable">',
            '<rect/>',
        '</g>'
    ].join(''),

	defaults: joint.util.deepSupplement({ 
        type: 'ui.Div',
        nameClass: "",
        parent: "",
        size: {
        	width: 200, height: 200	
        },
        attrs: {
            rect: { width: 200, height: 200, rx: 0, ry: 0, 'stroke-width': 2, stroke: '#000000', fill: '#ffffff'}
        }
    }, joint.shapes.basic.Generic.prototype.defaults)
});

joint.shapes.ui.DivView = joint.dia.ElementView.extend({

    template : [
        '<div class="ui-div">',
            '<button class="delete">x</button>',
            '<div class="resize"><i class="glyphicon glyphicon-fullscreen"></i></div>',
            '<button class="copy">+</button>',
        '</div>'
    ].join(''),

    initialize: function() {
        _.bindAll(this, 'updateDivView');
        joint.dia.ElementView.prototype.initialize.apply(this, arguments);

        this.$box = $(_.template(this.template)());
        this.$box.find('.delete').on('mousedown', _.bind(this.model.remove, this.model));
        
        // Update the box position whenever the underlying model changes.
        this.model.on('change', this.updateDivView, this);
        // Remove the box when the model gets removed from the graph.
        this.model.on('remove', this.removeDivView, this);

        this.updateDivView();
    },
    render: function() {
        joint.dia.ElementView.prototype.render.apply(this, arguments);
        this.paper.$el.prepend(this.$box);
        this.updateDivView();
        return this;
    },
    updateDivView: function() {
        // Set the position and dimension of the box so that it covers the JointJS element.
        var bbox = this.model.getBBox();
        // Example of updating the HTML with a data stored in the cell model.
        this.$box.css({
            width: bbox.width,
            height: bbox.height,
            left: bbox.x,
            top: bbox.y,
            transform: 'rotate(' + (this.model.get('angle') || 0) + 'deg)'
        });
    },
    removeDivView: function() {
        this.$box.remove();
    }
});

joint.shapes.ui.TextBox = joint.shapes.basic.Generic.extend({
     markup: [
        '<g class="scalable">',
            '<rect/>',
        '</g>',
        '<text class="name">TextBox</text>'
    ].join(''),

    defaults: joint.util.deepSupplement({
        type: 'ui.TextBox',
        nameClass: 'form-control ',
        size: { width: 150, height: 30 },
        name: 'TextBox',
        parent: "",
        attrs: {
            rect: { width: 150, height: 30, rx: 0, ry: 0, fill: "#ffffff", stroke: "#000000", 'stroke-dasharray': "none", 'stroke-width': 2 },
            text: { fill: '#000000', y: 20, 'text-anchor': 'left', x: 4, 'font-family': 'Arial', 'font-size': 16 }
        }
    }, joint.shapes.basic.Generic.prototype.defaults)
});

joint.shapes.ui.TextBoxView = joint.dia.ElementView.extend({

    template : [
        '<div class="ui-textbox">',
            '<button class="delete">x</button>',
            '<input class="name" type="text" />',
            '<div class="resize"><i class="glyphicon glyphicon-fullscreen"></i></div>',
            '<button class="copy">+</button>',
        '</div>'
    ].join(''),

    initialize: function() {
        _.bindAll(this, 'updateTextBoxView');
        joint.dia.ElementView.prototype.initialize.apply(this, arguments);

        this.$box = $(_.template(this.template)());
        // Prevent paper from handling pointerdown.
        this.$box.find('input').on('mousedown click', function(evt) { evt.stopPropagation(); });
        // This is an example of reacting on the input change and storing the input data in the cell model.
        this.$box.find('input').on('change', _.bind(function(evt) {
            this.model.set('name', $(evt.target).val());
            this.model.attr('text/html', $(evt.target).val());
            $(evt.target).val("");
        }, this));
        this.$box.find('input').on('focus', _.bind(function(evt) {
            $(evt.target).val(this.model.attr('.name/text'));
            this.model.attr('text/fill-opacity', 0);
        }, this));
        this.$box.find('input').on('focusout', _.bind(function(evt) {
            this.model.attr('text/fill-opacity', 1);
            $(evt.target).val("");
        }, this));
        this.$box.find('.delete').on('mousedown', _.bind(this.model.remove, this.model));
        // Update the box position whenever the underlying model changes.
        this.model.on('change', this.updateTextBoxView, this);
        // Remove the box when the model gets removed from the graph.
        this.model.on('remove', this.removeTextBoxView, this);

        this.updateTextBoxView();
    },
    render: function() {
        joint.dia.ElementView.prototype.render.apply(this, arguments);
        this.paper.$el.prepend(this.$box);
        this.updateTextBoxView();
        return this;
    },
    updateTextBoxView: function() {
        // Set the position and dimension of the box so that it covers the JointJS element.
        var bbox = this.model.getBBox();
        // Example of updating the HTML with a data stored in the cell model.
        this.$box.css({
            width: bbox.width,
            height: bbox.height,
            left: bbox.x,
            top: bbox.y,
            transform: 'rotate(' + (this.model.get('angle') || 0) + 'deg)'
        });
    },
    removeTextBoxView: function() {
        this.$box.remove();
    }
});

joint.shapes.ui.TextArea = joint.shapes.basic.Generic.extend({
     markup: [
        '<g class="scalable">',
            '<rect/>',
        '</g>',
        '<text class="name">TextArea</text>'
    ].join(''),

    defaults: joint.util.deepSupplement({
        type: 'ui.TextArea',
        nameClass: 'form-control',
        size: { width: 150, height: 60 },
        name: 'TextArea',
        parent: "",
        attrs: {
            rect: { width: 150, height: 60, rx: 0, ry: 0, fill: "#ffffff", stroke: "#000000", 'stroke-dasharray': "none", 'stroke-width': 2 },
            text: { fill: '#000000', 'text-anchor': 'left', y: 20, x: 4, 'font-family': 'Arial', 'font-size': 16 }
        }
    }, joint.shapes.basic.Generic.prototype.defaults)
});

joint.shapes.ui.TextAreaView = joint.dia.ElementView.extend({

    template : [
        '<div class="ui-textarea">',
            '<button class="delete">x</button>',
            '<textarea class="name" onkeyup="textAreaAdjust(this)"></textarea>',
            '<div class="resize"><i class="glyphicon glyphicon-fullscreen"></i></div>',
            '<button class="copy">+</button>',
        '</div>'
    ].join(''),

    initialize: function() {
        _.bindAll(this, 'updateTextAreaView');
        joint.dia.ElementView.prototype.initialize.apply(this, arguments);

        this.$box = $(_.template(this.template)());
        // Prevent paper from handling pointerdown.
        this.$box.find('textarea').on('mousedown click', function(evt) { evt.stopPropagation(); });
        // This is an example of reacting on the input change and storing the input data in the cell model.
        this.$box.find('textarea').on('change', _.bind(function(evt) {
            this.model.set('name', $(evt.target).val());
            this.model.attr('text/html', $(evt.target).val());
            $(evt.target).val("");
        }, this));
        this.$box.find('textarea').on('focus', _.bind(function(evt) {
             $(evt.target).val(this.model.attr('.name/text'));
            this.model.attr('text/fill-opacity', 0);
        }, this));
        this.$box.find('textarea').on('focusout', _.bind(function(evt) {
            this.model.attr('text/fill-opacity', 1);
            $(evt.target).val("");
        }, this));
        this.$box.find('.delete').on('mousedown', _.bind(this.model.remove, this.model));
        // Update the box position whenever the underlying model changes.
        this.model.on('change', this.updateTextAreaView, this);
        // Remove the box when the model gets removed from the graph.
        this.model.on('remove', this.removeTextAreaView, this);

        this.updateTextAreaView();
    },
    render: function() {
        joint.dia.ElementView.prototype.render.apply(this, arguments);
        this.paper.$el.prepend(this.$box);
        this.updateTextAreaView();
        return this;
    },
    updateTextAreaView: function() {
        // Set the position and dimension of the box so that it covers the JointJS element.
        var bbox = this.model.getBBox();
        // Example of updating the HTML with a data stored in the cell model.
        this.$box.css({
            width: bbox.width,
            height: bbox.height,
            left: bbox.x,
            top: bbox.y,
            transform: 'rotate(' + (this.model.get('angle') || 0) + 'deg)'
        });
    },
    removeTextAreaView: function() {
        this.$box.remove();
    }
});

joint.shapes.ui.Label = joint.shapes.basic.Generic.extend({
     markup: [
        '<g class="rotatable">',
            '<text class="name">Label</text>',
        '</g>'
    ].join(''),

    defaults: joint.util.deepSupplement({
        type: 'ui.Label',
        size: { width: 100, height: 30 },
        name: 'Label',
        nameClass: "",
        parent: "",
        attrs: {
            text: { fill: '#000000', y: 20, 'text-anchor': 'middle', x: 50, 'font-family': 'Arial', 'font-size': 16 }
        }
    }, joint.shapes.basic.Generic.prototype.defaults)
});

joint.shapes.ui.LabelView = joint.dia.ElementView.extend({

    template : [
        '<div class="ui-label">',
            '<button class="delete">x</button>',
            '<input class="name" type="text" />',
            '<button class="copy">+</button>',
        '</div>'
    ].join(''),

    initialize: function() {
        _.bindAll(this, 'updateLabelView');
        joint.dia.ElementView.prototype.initialize.apply(this, arguments);

        this.$box = $(_.template(this.template)());
        // Prevent paper from handling pointerdown.
        this.$box.find('input').on('mousedown click', function(evt) { evt.stopPropagation(); });
        // This is an example of reacting on the input change and storing the input data in the cell model.
        this.$box.find('input').on('change', _.bind(function(evt) {
            this.model.set('text', $(evt.target).val());
            this.model.attr('text/html', $(evt.target).val());
            $(evt.target).val("");
        }, this));
        this.$box.find('input').on('focus', _.bind(function(evt) {
            this.model.attr('text/fill-opacity', 0);
        }, this));
        this.$box.find('input').on('focusout', _.bind(function(evt) {
            this.model.attr('text/fill-opacity', 1);
        }, this));
        this.$box.find('.delete').on('mousedown', _.bind(this.model.remove, this.model));
        // Update the box position whenever the underlying model changes.
        this.model.on('change', this.updateLabelView, this);
        // Remove the box when the model gets removed from the graph.
        this.model.on('remove', this.removeLabelView, this);

        this.updateLabelView();
    },
    render: function() {
        joint.dia.ElementView.prototype.render.apply(this, arguments);
        this.paper.$el.prepend(this.$box);
        this.updateLabelView();
        return this;
    },
    updateLabelView: function() {
        // Set the position and dimension of the box so that it covers the JointJS element.
        var bbox = this.model.getBBox();
        // Example of updating the HTML with a data stored in the cell model.
        this.$box.css({
            width: bbox.width,
            height: bbox.height,
            left: bbox.x,
            top: bbox.y,
            transform: 'rotate(' + (this.model.get('angle') || 0) + 'deg)'
        });
    },
    removeLabelView: function() {
        this.$box.remove();
    }
});

joint.shapes.ui.RadioChecked = joint.shapes.basic.Generic.extend({
     markup: [
        '<g class="rotatable">',
            '<circle class="parent"/>',
            '<circle class="children"/>',
            '<text class="name">Radio Checked</text>',
        '</g>'
    ].join(''),

    defaults: joint.util.deepSupplement({
        type: 'ui.RadioChecked',
        size: { width: 20, height: 20 },
        name: 'Radio Checked',
        parent: "",
        attrs: {
            '.parent': { cy: 10, cx: 10, r: 10, fill: "#ffffff", stroke: "black", 'stroke-dasharray': "none", 'stroke-width': 2 },
            '.children': { cy: 10, cx: 10, r: 5, fill: "black", stroke: "none", 'stroke-dasharray': "none" },
            text: { fill: '#000000', y: 20, x: 24, 'text-anchor': 'left', 'font-family': 'Arial', 'font-size': 16 }
        }
    }, joint.shapes.basic.Generic.prototype.defaults)
});

joint.shapes.ui.RadioCheckedView = joint.dia.ElementView.extend({

    template : [
        '<div class="ui-radio-checked">',
            '<button class="delete">x</button>',
            '<input class="name" type="text" />',
            '<button class="copy">+</button>',
        '</div>'
    ].join(''),

    initialize: function() {
        _.bindAll(this, 'updateRadioCheckedView');
        joint.dia.ElementView.prototype.initialize.apply(this, arguments);

        this.$box = $(_.template(this.template)());
        // Prevent paper from handling pointerdown.
        this.$box.find('input').on('mousedown click', function(evt) { evt.stopPropagation(); });
        // This is an example of reacting on the input change and storing the input data in the cell model.
        this.$box.find('input').on('change', _.bind(function(evt) {
            this.model.set('text', $(evt.target).val());
            this.model.attr('text/html', $(evt.target).val());
            $(evt.target).val("");
        }, this));
        this.$box.find('input').on('focus', _.bind(function(evt) {
            this.model.attr('text/fill-opacity', 0);
        }, this));
        this.$box.find('input').on('focusout', _.bind(function(evt) {
            this.model.attr('text/fill-opacity', 1);
        }, this));
        this.$box.find('.delete').on('mousedown', _.bind(this.model.remove, this.model));
        // Update the box position whenever the underlying model changes.
        this.model.on('change', this.updateRadioCheckedView, this);
        // Remove the box when the model gets removed from the graph.
        this.model.on('remove', this.removeRadioCheckedView, this);

        this.updateRadioCheckedView();
    },
    render: function() {
        joint.dia.ElementView.prototype.render.apply(this, arguments);
        this.paper.$el.prepend(this.$box);
        this.updateRadioCheckedView();
        return this;
    },
    updateRadioCheckedView: function() {
        // Set the position and dimension of the box so that it covers the JointJS element.
        var bbox = this.model.getBBox();
        // Example of updating the HTML with a data stored in the cell model.
        this.$box.css({
            width: bbox.width,
            height: bbox.height,
            left: bbox.x,
            top: bbox.y,
            transform: 'rotate(' + (this.model.get('angle') || 0) + 'deg)'
        });
    },
    removeRadioCheckedView: function() {
        this.$box.remove();
    }
});

joint.shapes.ui.Radio = joint.shapes.basic.Generic.extend({
     markup: [
        '<g class="rotatable">',
            '<circle />',
            '<text class="name">Radio</text>',
        '</g>'
    ].join(''),

    defaults: joint.util.deepSupplement({
        type: 'ui.Radio',
        size: { width: 20, height: 20 },
        name: 'Radio',
        parent: "",
        attrs: {
            circle: { cy: 10, cx: 10, r: 10, fill: "#ffffff", stroke: "black", 'stroke-dasharray': "none", 'stroke-width': 2 },
            text: { fill: '#000000', y: 20, x: 24, 'text-anchor': 'left', 'font-family': 'Arial', 'font-size': 16 }
        }
    }, joint.shapes.basic.Generic.prototype.defaults)
});

joint.shapes.ui.RadioView = joint.dia.ElementView.extend({

    template : [
        '<div class="ui-radio">',
            '<button class="delete">x</button>',
            '<input class="name" type="text" />',
            '<button class="copy">+</button>',
        '</div>'
    ].join(''),

    initialize: function() {
        _.bindAll(this, 'updateRadioView');
        joint.dia.ElementView.prototype.initialize.apply(this, arguments);

        this.$box = $(_.template(this.template)());
        // Prevent paper from handling pointerdown.
        this.$box.find('input').on('mousedown click', function(evt) { evt.stopPropagation(); });
        // This is an example of reacting on the input change and storing the input data in the cell model.
        this.$box.find('input').on('change', _.bind(function(evt) {
            this.model.set('text', $(evt.target).val());
            this.model.attr('text/html', $(evt.target).val());
            $(evt.target).val("");
        }, this));
        this.$box.find('input').on('focus', _.bind(function(evt) {
            this.model.attr('text/fill-opacity', 0);
        }, this));
        this.$box.find('input').on('focusout', _.bind(function(evt) {
            this.model.attr('text/fill-opacity', 1);
        }, this));
        this.$box.find('.delete').on('mousedown', _.bind(this.model.remove, this.model));
        // Update the box position whenever the underlying model changes.
        this.model.on('change', this.updateRadioView, this);
        // Remove the box when the model gets removed from the graph.
        this.model.on('remove', this.removeRadioView, this);

        this.updateRadioView();
    },
    render: function() {
        joint.dia.ElementView.prototype.render.apply(this, arguments);
        this.paper.$el.prepend(this.$box);
        this.updateRadioView();
        return this;
    },
    updateRadioView: function() {
        // Set the position and dimension of the box so that it covers the JointJS element.
        var bbox = this.model.getBBox();
        // Example of updating the HTML with a data stored in the cell model.
        this.$box.css({
            width: bbox.width,
            height: bbox.height,
            left: bbox.x,
            top: bbox.y,
            transform: 'rotate(' + (this.model.get('angle') || 0) + 'deg)'
        });
    },
    removeRadioView: function() {
        this.$box.remove();
    }
});

joint.shapes.ui.CheckboxChecked = joint.shapes.basic.Generic.extend({
     markup: [
        '<g class="rotatable">',
            '<rect />',
            '<text class="checked">&#10003;</text>',
            '<text class="name">Checkbox Checked</text>',
        '</g>'
    ].join(''),

    defaults: joint.util.deepSupplement({
        type: 'ui.CheckboxChecked',
        size: { width: 20, height: 20 },
        name: 'Checkbox Checked',
        parent: "",
        attrs: {
            rect: { width: 20, height: 20, fill: "#ffffff", stroke: "black", 'stroke-dasharray': "none", 'stroke-width': 2 },
            '.name': { fill: '#000000', y: 20, 'text-anchor': 'left', x: 24, 'font-family': 'Arial', 'font-size': 16 },
            '.checked': { x: 4, y: 16, 'font-family': 'Arial', 'font-size': 18, 'fill': '#000000', 'font-weight': 'bold' }
        }
    }, joint.shapes.basic.Generic.prototype.defaults)
});

joint.shapes.ui.CheckboxCheckedView = joint.dia.ElementView.extend({

    template : [
        '<div class="ui-checkbox-checked">',
            '<button class="delete">x</button>',
            '<input class="name" type="text" />',
            '<button class="copy">+</button>',
        '</div>'
    ].join(''),

    initialize: function() {
        _.bindAll(this, 'updateCheckboxCheckedView');
        joint.dia.ElementView.prototype.initialize.apply(this, arguments);

        this.$box = $(_.template(this.template)());
        // Prevent paper from handling pointerdown.
        this.$box.find('input').on('mousedown click', function(evt) { evt.stopPropagation(); });
        // This is an example of reacting on the input change and storing the input data in the cell model.
        this.$box.find('input').on('change', _.bind(function(evt) {
            this.model.set('name', $(evt.target).val());
            this.model.attr('.name/text', $(evt.target).val());
            $(evt.target).val("");
        }, this));
        this.$box.find('input').on('focus', _.bind(function(evt) {
            this.model.attr('.name/fill-opacity', 0);
        }, this));
        this.$box.find('input').on('focusout', _.bind(function(evt) {
            this.model.attr('.name/fill-opacity', 1);
            $(evt.target).val("");
        }, this));
        this.$box.find('.delete').on('mousedown', _.bind(this.model.remove, this.model));
        // Update the box position whenever the underlying model changes.
        this.model.on('change', this.updateCheckboxCheckedView, this);
        // Remove the box when the model gets removed from the graph.
        this.model.on('remove', this.removeCheckboxCheckedView, this);

        this.updateCheckboxCheckedView();
    },
    render: function() {
        joint.dia.ElementView.prototype.render.apply(this, arguments);
        this.paper.$el.prepend(this.$box);
        this.updateCheckboxCheckedView();
        return this;
    },
    updateCheckboxCheckedView: function() {
        // Set the position and dimension of the box so that it covers the JointJS element.
        var bbox = this.model.getBBox();
        // Example of updating the HTML with a data stored in the cell model.
        this.$box.css({
            width: bbox.width,
            height: bbox.height,
            left: bbox.x,
            top: bbox.y,
            transform: 'rotate(' + (this.model.get('angle') || 0) + 'deg)'
        });
    },
    removeCheckboxCheckedView: function() {
        this.$box.remove();
    }
});

joint.shapes.ui.Checkbox = joint.shapes.basic.Generic.extend({
     markup: [
        '<g class="rotatable">',
            '<rect />',
            '<text class="name">Checkbox</text>',
        '</g>'
    ].join(''),

    defaults: joint.util.deepSupplement({
        type: 'ui.Checkbox',
        size: { width: 20, height: 20 },
        name: 'Checkbox',
        parent: "",
        attrs: {
            rect: { width: 20, height: 20, fill: "#ffffff", stroke: "black", 'stroke-dasharray': "none", 'stroke-width': 2 },
            text: { fill: '#000000', y: 20, 'text-anchor': 'left', x: 24, 'font-family': 'Arial', 'font-size': 16 }
        }
    }, joint.shapes.basic.Generic.prototype.defaults)
});

joint.shapes.ui.CheckboxView = joint.dia.ElementView.extend({

    template : [
        '<div class="ui-checkbox">',
            '<button class="delete">x</button>',
            '<input class="name" type="text" />',
            '<button class="copy">+</button>',
        '</div>'
    ].join(''),

    initialize: function() {
        _.bindAll(this, 'updateCheckboxView');
        joint.dia.ElementView.prototype.initialize.apply(this, arguments);

        this.$box = $(_.template(this.template)());
        // Prevent paper from handling pointerdown.
        this.$box.find('input').on('mousedown click', function(evt) { evt.stopPropagation(); });
        // This is an example of reacting on the input change and storing the input data in the cell model.
        this.$box.find('input').on('change', _.bind(function(evt) {
            this.model.set('text', $(evt.target).val());
            this.model.attr('text/html', $(evt.target).val());
            $(evt.target).val("");
        }, this));
        this.$box.find('input').on('focus', _.bind(function(evt) {
            this.model.attr('text/fill-opacity', 0);
        }, this));
        this.$box.find('input').on('focusout', _.bind(function(evt) {
            this.model.attr('text/fill-opacity', 1);
        }, this));
        this.$box.find('.delete').on('mousedown', _.bind(this.model.remove, this.model));
        // Update the box position whenever the underlying model changes.
        this.model.on('change', this.updateCheckboxView, this);
        // Remove the box when the model gets removed from the graph.
        this.model.on('remove', this.removeCheckboxView, this);

        this.updateCheckboxView();
    },
    render: function() {
        joint.dia.ElementView.prototype.render.apply(this, arguments);
        this.paper.$el.prepend(this.$box);
        this.updateCheckboxView();
        return this;
    },
    updateCheckboxView: function() {
        // Set the position and dimension of the box so that it covers the JointJS element.
        var bbox = this.model.getBBox();
        // Example of updating the HTML with a data stored in the cell model.
        this.$box.css({
            width: bbox.width,
            height: bbox.height,
            left: bbox.x,
            top: bbox.y,
            transform: 'rotate(' + (this.model.get('angle') || 0) + 'deg)'
        });
    },
    removeCheckboxView: function() {
        this.$box.remove();
    }
});

joint.shapes.ui.SelectBox = joint.shapes.basic.Generic.extend({
	 markup: [
       '<g class="scalable">',
           '<rect/>',
           '<path/>',
       '</g>',
       '<text class="name">Select Box</text>'
   ].join(''),

   defaults: joint.util.deepSupplement({
       type: 'ui.SelectBox',
       size: { width: 150, height: 30 },
       name: 'Select Box',
       nameClass: "form-control",
       parent: "",
       attrs: {
           rect: { width: 150, height: 30, fill: "#eeeeee", stroke: "black", 'stroke-dasharray': "none", 'stroke-width': 2 },
           text: { x: 10, y: 20, fill: '#000000', 'text-anchor': 'left', 'font-family': 'Arial', 'font-size': 16 },
           path: { d: "M 130 10 L 140 10 L 135 20 L 130 10", stroke: "black", 'stroke-width': "1", fill: "black" }
       },
       connect: []
   }, joint.shapes.basic.Generic.prototype.defaults)
});

joint.shapes.ui.SelectBoxView = joint.dia.ElementView.extend({

   template : [
       '<div class="ui-selectbox">',
           '<button class="delete">x</button>',
           '<input class="name" type="text"/>',
           '<div class="resize"><i class="glyphicon glyphicon-fullscreen"></i></div>',
           '<button class="copy">+</button>',
       '</div>'
   ].join(''),

   initialize: function() {
       _.bindAll(this, 'updateSelectBoxView');
       joint.dia.ElementView.prototype.initialize.apply(this, arguments);
       this.$box = $(_.template(this.template)());
       // Prevent paper from handling pointerdown.
       this.$box.find('input').on('mousedown click', function(evt) { evt.stopPropagation(); });
       // This is an example of reacting on the input change and storing the input data in the cell model.
       this.$box.find('input').on('change', _.bind(function(evt) {
           this.model.set('name', $(evt.target).val());
           this.model.attr('text/html', $(evt.target).val());
           $(evt.target).val("");
       }, this));
       this.$box.find('input').on('focus', _.bind(function(evt) {
           this.model.attr('text/fill-opacity', 0);
       }, this));
       this.$box.find('input').on('focusout', _.bind(function(evt) {
           this.model.attr('text/fill-opacity', 1);
       }, this));
       this.$box.find('.delete').on('mousedown', _.bind(this.model.remove, this.model));
       // Update the box position whenever the underlying model changes.
       this.model.on('change', this.updateSelectBoxView, this);
       // Remove the box when the model gets removed from the graph.
       this.model.on('remove', this.removeSelectBoxView, this);

       this.updateSelectBoxView();
   },
   render: function() {
       joint.dia.ElementView.prototype.render.apply(this, arguments);
       this.paper.$el.prepend(this.$box);
       this.updateSelectBoxView();
       return this;
   },
   updateSelectBoxView: function() {
       // Set the position and dimension of the box so that it covers the JointJS element.
       var bbox = this.model.getBBox();
       // Example of updating the HTML with a data stored in the cell model.
       this.$box.css({
           width: bbox.width,
           height: bbox.height,
           left: bbox.x,
           top: bbox.y,
           transform: 'rotate(' + (this.model.get('angle') || 0) + 'deg)'
       });
   },
   removeSelectBoxView: function() {
       this.$box.remove();
   }
});


joint.shapes.ui.Table = joint.shapes.basic.Generic.extend({
	markup: [
        '<g class="scalable">',
            '<rect />',
            '<line />',
        '</g>',
        '<text />',
    ].join(''),

	defaults: joint.util.deepSupplement({ 
        type: 'ui.Table',
        row: 0,
        col: 0,
        nameClass: "table ",
        parent: "",
        size: {
        	width: 350, height: 100	
        },
        attrs: {
            rect: { width: 350, height: 100, rx: 0, ry: 0, 'stroke-width': 2, stroke: '#000000' },
            line: {'stroke-width': 1, stroke: '#000000' },
            text: {fill: '#000000', 'text-anchor': 'left', 'font-family': 'Arial', 'font-size': 16 },
        }
    }, joint.shapes.basic.Generic.prototype.defaults)
});

function createTableDynamic(row, column, width, height, text, rect) {
	if (text == "") {
		text = {fill: '#000000', 'text-anchor': 'left', 'font-family': 'Arial', 'font-size': 16};
	}
	if (rect == "") {
		rect = { width: width, height: height, rx: 0, ry: 0, 'stroke-width': 2, stroke: '#000000', fill: '#f2f2f2' };
	}
	var markupRow = markupColumn = markupText = templateInput = "";
	for (var i = 0; i < column - 1; i++) {
		markupColumn += '<line class="column' + i + '" x1="' + ((i + 1)/column) * width + '" y1="0" x2="' + ((i + 1)/column) * width + '" y2="' + height + '"/>';
	}
	for (var i = 0; i < row - 1; i++) {
		markupRow += '<line class="row' + i + '" x1="0" y1="' + ((i + 1)/row) * height  + '" x2="' + width + '" y2="' + ((i + 1)/row) * height + '"/>';
	}
	var dem = 0;
	for (var i = 0; i < row; i++) {
		for (var j = 0; j < column; j++) {
			markupText += '<text class="text' + dem + '" x="' + (((j/column) * width) + 5) + '" y="' + ((((i*2 + 1)/(row * 2)) * height) + 5) + '"> Data ' + dem + '</text>';
			dem++;
		}
	}
	joint.shapes.ui.Table = joint.shapes.basic.Generic.extend({
		markup: [
	        '<g class="scalable">',
	            '<rect />',
	            markupRow,
	            markupColumn,
	        '</g>',
	        markupText,    
	    ].join(''),

		defaults: joint.util.deepSupplement({ 
	        type: 'ui.Table',
	        row: row,
			col: column,
			nameClass: "table ",
			parent: "",
	        size: {
	        	width: width, height: height	
	        }
	    }, joint.shapes.basic.Generic.prototype.defaults)
	});
	joint.shapes.ui.TableView = joint.dia.ElementView.extend({

	    template : [
	        '<div class="ui-table">',
	            '<button class="delete">x</button>',
	            '<div class="resize"><i class="glyphicon glyphicon-fullscreen"></i></div>',
	            '<button class="copy">+</button>',
	        '</div>'
	    ].join(''),

	    initialize: function() {
	        _.bindAll(this, 'updateTableView');
	        joint.dia.ElementView.prototype.initialize.apply(this, arguments);
	        this.$box = $(_.template(this.template)());
	        this.model.attr({
	        	rect: { width: width, height: height, rx: rect["rx"], ry: rect["ry"], 'stroke-width': rect["stroke-width"], stroke: rect["stroke"], fill: rect["fill"] },
	            line: {'stroke-width': 1, stroke: '#000000' },
	            text: {fill: text["fill"], 'text-anchor': text["text-anchor"], 'font-family': text["font-family"], 'font-size': text["font-size"]}
	        });
	        this.$box.find('.delete').on('mousedown', _.bind(this.model.remove, this.model));
	        // Update the box position whenever the underlying model changes.
	        this.model.on('change', this.updateTableView, this);
	        // Remove the box when the model gets removed from the graph.
	        this.model.on('remove', this.removeTableView, this);

	        this.updateTableView();
	    },
	    render: function() {
	        joint.dia.ElementView.prototype.render.apply(this, arguments);
	        this.paper.$el.prepend(this.$box);
	        this.updateTableView();
	        return this;
	    },
	    updateTableView: function() {
	        // Set the position and dimension of the box so that it covers the JointJS element.
	        var bbox = this.model.getBBox();
	        // Example of updating the HTML with a data stored in the cell model.
	        this.$box.css({
	            width: bbox.width,
	            height: bbox.height,
	            left: bbox.x,
	            top: bbox.y,
	            transform: 'rotate(' + (this.model.get('angle') || 0) + 'deg)'
	        });
	    },
	    removeTableView: function() {
	        this.$box.remove();
	    }
	});
}