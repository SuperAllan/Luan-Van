joint.shapes.uml = {}

joint.shapes.uml.Actor = joint.shapes.basic.Generic.extend({

    markup: [
        '<g class="rotatable">',
            '<circle/>',
            '<line fill="#fcfcfc" stroke="black" stroke-dasharray="none" stroke-width="2" x1="21" x2="21" y1="25" y2="50"></line>',
            '<line fill="#fcfcfc" stroke="black" stroke-dasharray="none" stroke-width="2" x1="21" x2="6" y1="50" y2="70"></line>',
            '<line fill="#fcfcfc" stroke="black" stroke-dasharray="none" stroke-width="2" x1="21" x2="36" y1="50" y2="70"></line>',
            '<line fill="#fcfcfc" stroke="black" stroke-dasharray="none" stroke-width="2" x1="6" x2="36" y1="35" y2="35"></line>',
            '<text class="name">Actor</text>',
        '</g>'
    ].join(''),

    defaults: joint.util.deepSupplement({
        type: 'uml.Actor',
        size: { width: 43, height: 78 },
        name: 'Actor',
        role: "",
        level: "1",
        description: "",
        attrs: {
            circle: { cx: 21, cy: 15, fill: "#fcfcfc", r: 10, stroke: "black", 'stroke-dasharray': "none", 'stroke-width': 2 },
            '.name': { fill: 'black', y: 98, 'text-anchor': 'middle', x: 23, 'font-family': 'Arial', 'font-size': 16 }
        },
        shapes: {
            circle: { cx: 21, cy: 15, fill: "#fcfcfc", r: 10, stroke: "black", 'stroke-dasharray': "none", 'stroke-width': 2 },
            line: [{ 
                fill: "#fcfcfc", stroke: "black", 'stroke-dasharray': "none",'stroke-width': 2,'x1': 21,'x2': 21,'y1': 25,'y2': 50
            }, {
                fill: "#fcfcfc", stroke: "black", 'stroke-dasharray': "none",'stroke-width': 2,'x1': 21,'x2': 6,'y1': 50,'y2': 70
            }, {
                fill: "#fcfcfc", stroke: "black", 'stroke-dasharray': "none",'stroke-width': 2,'x1': 21,'x2': 36,'y1': 50,'y2': 70
            }, {
                fill: "#fcfcfc", stroke: "black", 'stroke-dasharray': "none",'stroke-width': 2,'x1': 6,'x2': 36,'y1': 35,'y2': 35
            }],
            text: { fill: 'black', y: 98, 'text-anchor': 'middle', x: 23 }
        }
    }, joint.shapes.basic.Generic.prototype.defaults)
});

joint.shapes.uml.ActorView = joint.dia.ElementView.extend({

    template : [
        '<div class="uml-actor">',
            '<button class="delete">x</button>',
            '<div class="relationship association"><img src="/luanvan/resources/img/association.png" /></div>',
            '<div class="relationship generalization"><img src="/luanvan/resources/img/generalization.png" /></div>',
            '<textarea class="name" onkeyup="textAreaAdjust(this)"></textarea>',
        '</div>'
    ].join(''),

    initialize: function() {
        _.bindAll(this, 'updateActorView');
        joint.dia.ElementView.prototype.initialize.apply(this, arguments);

        this.$box = $(_.template(this.template)());
        // Prevent paper from handling pointerdown.
        this.$box.find('textarea').on('mousedown click', function(evt) { evt.stopPropagation(); });
        // This is an example of reacting on the input change and storing the input data in the cell model.
        this.$box.find('textarea').on('change', _.bind(function(evt) {
            this.model.set('name', $(evt.target).val());
            this.model.attr('.name/text', $(evt.target).val());
            $(evt.target).val("");
        }, this));
        this.$box.find('textarea').on('focus', _.bind(function(evt) {
            this.model.attr('.name/fill-opacity', 0);
        }, this));
        this.$box.find('textarea').on('focusout', _.bind(function(evt) {
            this.model.attr('.name/fill-opacity', 1);
        }, this));
        this.$box.find('.delete').on('click', _.bind(this.model.remove, this.model));
        // Update the box position whenever the underlying model changes.
        this.model.on('change', this.updateActorView, this);
        // Remove the box when the model gets removed from the graph.
        this.model.on('remove', this.removeActorView, this);

        this.updateActorView();
    },
    render: function() {
        joint.dia.ElementView.prototype.render.apply(this, arguments);
        this.paper.$el.prepend(this.$box);
        this.updateActorView();
        return this;
    },
    updateActorView: function() {
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
    removeActorView: function() {
        this.$box.remove();
    }
});

joint.shapes.uml.Usecase = joint.shapes.basic.Generic.extend({

    markup: [
        '<g class="rotatable">',
            '<ellipse />',
            '<text class="name">Usecase</text>',
        '</g>'
    ].join(''),

	defaults: joint.util.deepSupplement({
		type: 'uml.Usecase',
        size: { width: 130, height: 60 },
        name: "Usecase",
        level: "1",
        description: "",
        payMoney: false,
        attrs: {
            ellipse: { stroke: 'black', 'stroke-width': 2, rx : 60, ry: 25, fill: 'white', cx: 65, cy: 30 },
            '.name': { fill: 'black', y: 82, 'text-anchor': 'middle', x: 66, 'font-family': 'Arial', 'font-size': 16 }
        },
        shapes: {
            ellipse: { stroke: 'black', 'stroke-width': 2, rx : 60, ry: 25, fill: 'white', cx: 65, cy: 30 },
            text: { fill: 'black', y: 82, 'text-anchor': 'middle', x: 66 }
        }
	}, joint.shapes.basic.Generic.prototype.defaults)
});

joint.shapes.uml.UsecaseView = joint.dia.ElementView.extend({

	template : [
		'<div class="uml-usecase">',
			'<button class="delete">x</button>',
            '<div class="relationship association"><img src="/luanvan/resources/img/association.png" /></div>',
            '<div class="relationship extend"><img src="/luanvan/resources/img/extend.png" /></div>',
            '<div class="relationship include"><img src="/luanvan/resources/img/include.png" /></div>',
            '<div class="relationship generalization"><img src="/luanvan/resources/img/generalization.png" /></div>',
			'<textarea class="name" onkeyup="textAreaAdjust(this)"></textarea>',
		'</div>'
	].join(''),

	initialize: function() {

		_.bindAll(this, 'updateUsecaseView');
        joint.dia.ElementView.prototype.initialize.apply(this, arguments);

        this.$box = $(_.template(this.template)());
        // Prevent paper from handling pointerdown.
        this.$box.find('textarea').on('mousedown click', function(evt) { evt.stopPropagation(); });
        // This is an example of reacting on the input change and storing the input data in the cell model.
        this.$box.find('textarea').on('change', _.bind(function(evt) {
            this.model.set('name', $(evt.target).val());
            this.model.attr('.name/text', $(evt.target).val());
            $(evt.target).val("");
        }, this));
        this.$box.find('textarea').on('focus', _.bind(function(evt) {
            this.model.attr('.name/fill-opacity', 0);
        }, this));
        this.$box.find('textarea').on('focusout', _.bind(function(evt) {
            this.model.attr('.name/fill-opacity', 1);
        }, this));
        this.$box.find('.delete').on('click', _.bind(this.model.remove, this.model));
        // Update the box position whenever the underlying model changes.
        this.model.on('change', this.updateUsecaseView, this);
        // Remove the box when the model gets removed from the graph.
        this.model.on('remove', this.removeUsecaseView, this);

        this.updateUsecaseView();
	},
	render: function() {
		joint.dia.ElementView.prototype.render.apply(this, arguments);
        this.paper.$el.prepend(this.$box);
        this.updateUsecaseView();
        return this;
	},
	updateUsecaseView: function() {
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
	removeUsecaseView: function() {
		this.$box.remove();
	}
});

joint.shapes.uml.Association = joint.dia.Link.extend({
    defaults: { 
        type: 'uml.Association',
        attrs: {
            '.connection': { 'stroke-width': 2, stroke: '#aaa', fill: 'transparent' },
            '.marker-vertices': { 'opacity': 0},
            '.marker-arrowhead': { 'opacity': 0 }
        },
        shapes: { '.connection': { 'stroke-width': 2, stroke: '#aaa', fill: 'transparent' } }
    }
});

joint.shapes.uml.Extend = joint.dia.Link.extend({
    defaults: { 
        type: 'uml.Extend',
        attrs: {
            '.connection': { 'stroke-width': 2, stroke: '#ccc', fill: 'transparent' },
            '.marker-target': { fill: 'black', d: 'M 10 0 L 0 5 L 10 10 z' },
            // '.marker-vertices': { 'display': 'none' }
        },
        shapes: {
            '.connection': { 'stroke-width': 2, stroke: '#ccc', fill: 'transparent' },
            '.marker-target': { fill: 'black', d: 'M 10 0 L 0 5 L 10 10 z' }
        }
    }
});

joint.shapes.uml.Include = joint.dia.Link.extend({
    defaults: { 
        type: 'uml.Include',
        attrs: { 
            '.connection': { 'stroke-width': 2, stroke: '#ccc', fill: 'transparent' },
            '.marker-target': { fill: 'black', d: 'M 10 0 L 0 5 L 10 10 z' },
            // '.marker-vertices': { 'display': 'none' }
        },
        shapes: {
            '.connection': { 'stroke-width': 2, stroke: '#ccc', fill: 'transparent' },
            '.marker-target': { fill: 'black', d: 'M 10 0 L 0 5 L 10 10 z' }
        }
    }
});

joint.shapes.uml.Generalization = joint.dia.Link.extend({
    defaults: { 
        type: 'uml.Generalization',
        attrs: {
            '.connection': { 'stroke-width': 2, stroke: '#ccc', fill: 'transparent' },
            '.marker-target': { fill: 'white', d: 'M 10 0 L 0 10 L 10 20 z' },
            // '.marker-vertices': { 'display': 'none' }
        },
        shapes: {
            '.connection': { 'stroke-width': 2, stroke: '#ccc', fill: 'transparent' },
            '.marker-target': { fill: 'white', d: 'M 10 0 L 0 10 L 10 20 z' }
        }
    }
});