(function() {
    function App() {

    }
    
    App.prototype.addConfiguration = function() {
    	var name = $('#newConfigName').val();
    	// TODO add additional validation?
    	if ( name === '' ) {
    		
    	} else {
    		$('#configTable').DataTable().row.add( { "configId": "-1", "configName":name } ).draw();
    	}
    }
    
    App.prototype.getTimePeriod = function() {
    	var timePeriod = document.getElementById("timePeriod");
    	var selectedTimePeriod = timePeriod.options[timePeriod.selectedIndex].value;
    	
    	return selectedTimePeriod;
    }
    
    App.prototype.removeAllConfigurations = function() {
    	$('#configTable').DataTable().clear().draw();
    }
    
    App.prototype.removeConfiguration = function() {
    	$('#configTable').DataTable().row('.selected').remove().draw();
    }
    
    App.prototype.updateConfigurations = function() {
    	$('#configTable').DataTable().destroy();
    	app.init();
    }
    
    App.prototype.saveConfigurations = function() {
    	//var transmitData = JSON.stringify($('#configTable').DataTable().rows().data());
    	//var obj1 = {configId:1,configName:"asdf"};
    	var transmitArr = [];
    	
    	var tableData = $('#configTable').DataTable().data();
    	for (var i = 0; i < tableData.length; i++) {
    		var obj2 = {configId: tableData[i].configId, configName: tableData[i].configName};
    		transmitArr.push(obj2);
    	}
    	var transmitData = JSON.stringify(transmitArr);
    	
    	$.ajax({
    		headers: { 
            	'Accept': 'application/json',
            	'Content-Type': 'application/json' 
        	},
    		method: "POST",
        	url: '/configuration/saveConfigurations/' + app.getTimePeriod(),
        	dataType: 'json',
        	data : transmitData,
        	success: function(data) {
        		app.updateConfigurations();
        	}
    	});
    }
    


    App.prototype.getData = function() {
        $.ajax({
        	
        		
        }).then(function(data) {
        	
        })
    };

    App.prototype.init = function() {
        $('#configTable').DataTable({
        	"ajax": {
        		"url":"/configuration/" + app.getTimePeriod(),
        		"dataSrc":""
        	},
            scrollY: 300,
            paging: false,
            sorting: false,
            searching: false,
            info: false,
            select: 'single',
            columns: [
                      { data: 'configId' },
                      { data: 'configName' }
            ]
            		
        });
    };

    window.app = new App;
})($);