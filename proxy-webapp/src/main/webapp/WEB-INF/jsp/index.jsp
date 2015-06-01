<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">

    <link  href="/css/ext-theme-crisp/ext-theme-crisp-all.css" rel="stylesheet" type="text/css"  />
    <script src="/js/ext-all-debug.js" type="text/javascript"></script>
    <script src="/js/extjs-utils.js" type="text/javascript"></script>
        <script type="text/javascript">

        Ext.onReady(function() {

        var locationStore = new Ext.data.JsonStore({
                autoLoad: true,
                fields: ['id', 'ip', 'port', 'requestCount', 'score', 'history', 'requestTime', 'addDate', 'lastUpdate', 'remoteIp'],
                sortInfo: { field: "id", direction: "ASC" },
                proxy: {
                    url: '/proxy/list',
                    type: 'ajax',

                }
        });

        var proxyGrid = Ext.create('Ext.grid.Panel', {
                        stateful: true,
                        store: locationStore,
                        layout: 'fit',
                        forceFit: true,
                        viewConfig: {
                            scrollOffset: 0,
                            forceFit: true
                        },
                        columns: [
                            {
                                text: 'ip:port',
                                sortable: true,
                                dataIndex: 'id'
                            },

                            {
                                text: 'requestCount',
                                sortable: true,
                                dataIndex: 'requestCount'
                            },
                            {
                                text: 'score',
                                sortable: true,
                                dataIndex: 'score'
                            },
                            {
                                text: 'request time',
                                sortable: true,
                                dataIndex: 'requestTime'
                            },
                            {
                                text: 'history',
                                sortable: true,
                                dataIndex: 'history'
                            },



                        ]
                    });


         Ext.create('Ext.form.Panel', {
                id:'mainPanel',
                renderTo : Ext.getBody(),
                bodyStyle : 'padding: 5px 5px 0 5px;',
                height : '500px',
                layout: 'fit',

                title : 'Proxy status',
                items : [
                    proxyGrid
                ]

            });


        });
    </script>


</head>
<body>


</body>
</html>