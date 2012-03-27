Ext.define('SD.model.StatusChange', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id',            type: 'int',    useNull: true},
        {name: 'subject_id',    type: 'int',    useNull: true},
        {name: 'status',        type: 'int',  useNull: true},
        {name: 'feature',       type: 'int',  useNull: true},
        {name: 'created',       type: 'date',   dateFormat: 'Y-m-d H:i:s'}
    ],
    proxy: {
        type: 'rest',
        url : '/rest/statuschanges'
    }
});