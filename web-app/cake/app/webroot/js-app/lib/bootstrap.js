Ext.override(Ext.data.reader.Json, {
    extractValues: function(data) {
        // if (data.created)
        //     data.created += "+0000";
        var fields = this.getFields(),
            i      = 0,
            length = fields.length,
            output = {},
            field, value;
        
        for (; i < length; i++) {
            field = fields[i];
            value = this.extractorFunctions[i](data);

            output[field.name] = value;
        }

        return output;
        // this.callOverridden();
    }
});

Ext.override(Ext.view.Table, {
    afterRender: function() {
        var me = this;

        me.callParent();
        me.mon(me.el, {
            scroll: me.fireBodyScroll,
            scope: me
        });
        if (!me.featuresMC &&
            (me.featuresMC.findIndex('ftype', 'unselectable') >= 0)) {
            me.el.unselectable();
        }

        me.attachEventsForFeatures();
    }
});
