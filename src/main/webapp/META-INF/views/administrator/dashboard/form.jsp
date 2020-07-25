<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<link rel="stylesheet" href="libraries/acme/css/chart.css"/>

<script type="text/javascript" src="libraries/chart.js/2.7.2/js/chart.min.js"></script>

<%-- <acme:form readonly="true"> --%>
	<acme:form-integer code="administrator.dashboard.form.label.totalNumberOfNotices" path="totalNumberOfNotices"/>
	<acme:form-integer code="administrator.dashboard.form.label.totalNumberOfTechnologyRecords" path="totalNumberOfTechnologyRecords"/>
	<acme:form-integer code="administrator.dashboard.form.label.totalNumberOfToolRecords" path="totalNumberOfToolRecords"/>
	
	
	<!-- Inquiries -->
	<acme:form-panel code="administrator.dashboard.form.label.inquiryInformation">
		<acme:form-double code="administrator.dashboard.form.label.minimumMoneyInquiries" path="minimumMoneyInquiries"/>
		<acme:form-double code="administrator.dashboard.form.label.maximumMoneyInquiries" path="maximumMoneyInquiries"/>
		<acme:form-double code="administrator.dashboard.form.label.averageMoneyInquiries" path="averageMoneyInquiries"/>
		<acme:form-double code="administrator.dashboard.form.label.standardDesviationMoneyInquiries" path="standardDesviationMoneyInquiries"/>
	</acme:form-panel>
	
	<!-- Overtures -->
	<acme:form-panel code="administrator.dashboard.form.label.overtureInformation">
		<acme:form-double code="administrator.dashboard.form.label.minimumMoneyOvertures" path="minimumMoneyOvertures"/>
		<acme:form-double code="administrator.dashboard.form.label.maximumMoneyOvertures" path="maximumMoneyOvertures"/>
		<acme:form-double code="administrator.dashboard.form.label.averageMoneyOvertures" path="averageMoneyOvertures"/>
		<acme:form-double code="administrator.dashboard.form.label.standardDesviationMoneyOvertures" path="standardDesviationMoneyOvertures"/>
	</acme:form-panel>
	
	<!-- Applications and investment rounds -->
	<acme:form-panel code="administrator.dashboard.form.label.applicationsAndInvestmentRounds">
		<acme:form-double code="administrator.dashboard.form.label.averageRoundsPerEntrepreneur" path="averageRoundsPerEntrepreneur"/>
		<acme:form-double code="administrator.dashboard.form.label.averageApplicationsPerEntrepreneur" path="averageApplicationsPerEntrepreneur"/>
		<acme:form-double code="administrator.dashboard.form.label.averageApplicationsPerInvestor" path="averageApplicationsPerInvestor"/>
	</acme:form-panel>
	
	<!-- Investment rounds by kind and applications per status ratios' charts -->
	
	<h2>
		<acme:message code="administrator.dashboard.form.label.roundsByKindRatiosChart"/>
	</h2>	
	
	<div>
		<canvas id="roundsByKindRatiosChart"></canvas>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			var data = {
				labels : ["Seed","Angel","Series A","Series B","Series C","Bridge"],
				datasets : [
					{
						label: 'Rounds',
						data : [
							<jstl:out value="${ratioSeedInvestmentRounds}"/>,
							<jstl:out value="${ratioAngelInvestmentRounds}"/>,
							<jstl:out value="${ratioSeriesAInvestmentRounds}"/>,
							<jstl:out value="${ratioSeriesBInvestmentRounds}"/>,
							<jstl:out value="${ratioSeriesCInvestmentRounds}"/>,
							<jstl:out value="${ratioBridgeInvestmentRounds}"/>,
						],
						backgroundColor : 'LightSkyBlue'
						
					},
				]
			};
			var options = {
				scales : {
					yAxes : [
						{
							ticks : {
								suggestedMin : 0.0,
								suggestedMax : 1.0
							}
						}
					]
				}
			};
			var canvas,context;
			canvas = document.getElementById("roundsByKindRatiosChart");
			context = canvas.getContext("2d");
			new Chart(context, {
				type : "bar",
				data : data,
				options : options
			});
			
		});
	</script>
	
	<h2>
		<acme:message code="administrator.dashboard.form.label.applicationsByStatusRatiosChart"/>
	</h2>	
	
	<div>
		<canvas id="applicationsByStatusRatiosChart"></canvas>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			var data = {
				labels : ["Accepted","Rejected","Pending"],
				datasets : [
					{
						label: 'Rounds',
						data : [
							<jstl:out value="${ratioAcceptedApplications}"/>,
							<jstl:out value="${ratioRejectedApplications}"/>,
							<jstl:out value="${ratioPendingApplications}"/>,
						],
						backgroundColor : 'LightSkyBlue'
						
					},
				]
			};
			var options = {
				scales : {
					yAxes : [
						{
							ticks : {
								suggestedMin : 0.0,
								suggestedMax : 1.0
							}
						}
					]
				}
			};
			var canvas,context;
			canvas = document.getElementById("applicationsByStatusRatiosChart");
			context = canvas.getContext("2d");
			new Chart(context, {
				type : "bar",
				data : data,
				options : options
			});
			
		});
	</script>
	
	<!-- Listing indicators -->
	<h2>
		<acme:message code="administrator.dashboard.form.label.chart1"/>
	</h2>	
	
	<div>
		<canvas id="canvas1"></canvas>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			var data = {
				labels : ["CLOSED-SOURCE","OPEN-SOURCE"],
				datasets : [
					{
						label: 'Technologies',
						data : [
							<jstl:out value="${openSourceRatioTechnologies}"/>,
							<jstl:out value="${closedSourceRatioTechnologies}"/>,
						],
						backgroundColor : 'LightSkyBlue'
						
					},
				]
			};
			var options = {
				scales : {
					yAxes : [
						{
							ticks : {
								suggestedMin : 0.0,
								suggestedMax : 1.0
							}
						}
					]
				}
			};
			var canvas,context;
			canvas = document.getElementById("canvas1");
			context = canvas.getContext("2d");
			new Chart(context, {
				type : "bar",
				data : data,
				options : options
			});
			
		});
	</script>
	
	<!-- Chart Application Ratios -->
	<h2>
		<acme:message code="administrator.dashboard.form.label.chart2"/>
	</h2>	
	
	<div>
		<canvas id="canvas2"></canvas>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			var data = {
				labels : ["CLOSED-SOURCE","OPEN-SOURCE"],
				datasets : [
					{
						label: 'Tools',
						data : [
							<jstl:out value="${openSourceRatioTools}"/>,
							<jstl:out value="${closedSourceRatioTools}"/>,
						],
						backgroundColor : 'LightSkyBlue'
						
					},
				]
			};
			var options = {
				scales : {
					yAxes : [
						{
							ticks : {
								suggestedMin : 0.0,
								suggestedMax : 1.0
							}
						}
					]
				}
			};
			var canvas,context;
			canvas = document.getElementById("canvas2");
			context = canvas.getContext("2d");
			new Chart(context, {
				type : "bar",
				data : data,
				options : options
			});
			
		});
	</script>
	
	<!-- Chart TechsAndTools per Sector Ratios -->
	<div class="graficas">
	     <div class="chart">
	       <acme:form-label code="administrator.dashboard.form.label.chartTitle" path="chartTitle"/>
	       <canvas id="chart1" ></canvas>
	     </div>
	</div>
	
	
	<!-- Librería personalizada para mostrar gráfica usando parámetros cualquiera -->
	<script>
	//a un color tipo rgba(1,1,1,a) le cambia a
function cambiaColor(color,a){
  if(a.match(/^([0-1]||0\.\d)$/)){//Comprueba que a es correcto
    return color.replace(/\d\)/, a+")");//cambia el a de rgba
  }
  console.log("Fallo al cambiar color "+color+" con "+a);
  return color;
}

window.chartColors = {
	red: 'rgba(255, 99, 132, 1)',
	orange: 'rgba(255, 159, 64, 1)',
	yellow: 'rgba(255, 205, 86, 1)',
	green: 'rgba(75, 192, 192, 1)',
	blue: 'rgba(54, 162, 235, 1)',
	purple: 'rgba(153, 102, 255, 1)',
	grey: 'rgba(201, 203, 207, 1)'
};
//var COLORS = ['#4dc9f6','#f67019','#f53794','#537bc4','#acc236','#166a8f','#00a950','#58595b','#8549ba'];


function construyeGrafica(chart, tipo, dataY,dataX, labels, titulo, stacked){
  chart.height = 350;  chart.width = 350;
  return grafica(chart, tipo, dataY,dataX, labels, titulo, stacked[0], stacked[1]);
}


function grafica(ctx, tipo, dataY, dataX, labels, titulo, yStacked, xStacked){
	var data = datosGrafica(dataY, dataX, labels);
	var options = {
			scales: {
        yAxes: [{
          stacked: yStacked,
	        ticks: {
	        	suggestedMin: 0,
	        	//suggestedMax: 5,
				stepSize: 1
	        }/*,
	        afterBuildTicks: function(humdaysChart) {    
	            humdaysChart.ticks = [0,1,2,3,4,5];
	        }*/
        }],
        xAxes: [{
          stacked: xStacked
        }]
      },
      tooltips:{
        enabled:false
      },
			title:{
				display:false,
				text:titulo,
				fontSize: 20
			},
			elements: {
          line: {
            tension: 0 // disables bezier curves
          }
        },
			legend:{
				display:true
			},
			responsive : false
		}

	Chart.defaults.global.defaultFontColor = '#3a3737';
	Chart.defaults.global.defaultFontFamily = 'Lato';
  //Chart.defaults.global.defaultFontSize = 12;


	var c = new Chart(ctx, {
		type: tipo,
		data: data,
		options: options
		});
	return c
}


//Coge los datos y los transforma para hacerlos compatibles con Chart.js

function datosGrafica(dataY,dataX, labels){
	//El método ordena da igual si los inversores y compañias tienen los mismos sectores en la BD
  //var datos = ordena(dataY,dataX); 
	
  var datos = [dataX[0], [dataY[0], dataY[1]]];//[sectores, [numComp, numInv]] 
  
  var red = window.chartColors.red, blue = window.chartColors.blue; 
  
	return {
		labels: datos[0],
		datasets: [{
      label: labels[0],//Nombre en la leyenda
			data: datos[1][0],
      backgroundColor: cambiaColor(red,"0.6"),
      hoverBackgroundColor:cambiaColor(red,"0.8"),
			borderColor:cambiaColor(red,"1"),
			borderWidth: 1
		},
    {
      label: labels[1],//Nombre en la leyenda
      data: datos[1][1],
      backgroundColor: cambiaColor(blue,"0.6"),
      hoverBackgroundColor:cambiaColor(blue,"0.8"),
			borderColor:cambiaColor(blue,"1"),
			borderWidth: 1
		}
  ]}
}

//Este método mezcla los sectores de las compañias e inversores y los ordena.
//Si inversor/compania no tiene un sector que compañia/compania si, le añadimos un 0 en la posición correcta de manera que los datos sean correctos.

function ordena(dataY,datosX){
	  //Copiamos los arrays
	  var sectores = datosX[0].slice(), sectoresCompanias = datosX[0].slice(), sectoresInversores = datosX[1].slice();//Sectores
	  var datosCompanias = dataY[0].slice(), datosInversores = dataY[1].slice();//nº compañia y nº inversores 
	  
	  //Por cada sector del segundo array de sectores lo metemos en el otro si no lo tiene.
	  
	  for(var i=0 ; i< sectoresInversores.length ; i++){
	    var str = sectoresInversores[i];
	    if(!sectores.includes(str)){
	    	sectores.push(str);
	    }
	  }
	  sectores.sort();//Lo ordenamos

	  // Luego creamos los datos data1 y data2 de manera que tenga los datos correctos de nº inversores y compañias, añadiendo 0 por
	  // cada sector que no tenga inversor  / compañia.
	  for(var i=0 ; i<sectores.length ; i++)
	  {
	    var str = sectores[i];
	    
	    if(!sectoresCompanias.includes(str))  datosCompanias.splice(i, 0, 0);

	    if(!sectoresInversores.includes(str))  datosInversores.splice(i, 0, 0);
	  }
	  
	  var res = [sectores,[datosCompanias,datosInversores]];
	  console.log(res);
	  return res;
	}
	</script>
	<script type="text/javascript">
		var techEtiquetes = new Array();
		<jstl:forEach items="${technologySectors}" var="item">
		techEtiquetes.push("${item}");
		</jstl:forEach>
	
		var techData = new Array();	
		<jstl:forEach items="${numberTechnologiesBySector}" var="item">
		techData.push(${item});
		</jstl:forEach>
	
		var toolEtiquetes = new Array();
		<jstl:forEach items="${toolSectors}" var="item">
		toolEtiquetes.push("${item}");
		</jstl:forEach>
	
		var toolData = new Array();	
		<jstl:forEach items="${numberToolsBySector}" var="item">
		toolData.push(${item});
		</jstl:forEach>
		
		
		var ctx = document.getElementById('chart1');	

		var data = [techData, toolData];
		var ets = [techEtiquetes, toolEtiquetes];
		var labels = ["Tecnologías","Herramientas"];
		var title = "Tecnologías/Herramientas por sector";
		
		var chart = construyeGrafica(ctx, "bar", data,ets,labels, "",[false,false]);

	</script>
	
		<!-- Chart Application Time Series -->
	<head>
	    <title>Line Chart</title>
	    <script src="http://cdnjs.cloudflare.com/ajax/libs/moment.js/2.13.0/moment.min.js"></script>
	    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.bundle.js"></script>
	    <style>
	        canvas {
	            -moz-user-select: none;
	            -webkit-user-select: none;
	            -ms-user-select: none;
	        }
	    </style>
	</head>
	
	<h2>
		<acme:message code="administrator.dashboard.form.label.timeSeries"/>
	</h2>	
	

	<div style="width:75%;">
    <canvas id="timeSeries"></canvas>
	</div>
	
	<script>

		<!-- Pending -->
		var dateListOfPendingApplications = new Array();
		<jstl:forEach items="${dateListOfPendingApplications}" var="item">
		dateListOfPendingApplications.push("${item}");
		</jstl:forEach>
		
		var numberListOfPendingApplications = new Array();
		<jstl:forEach items="${numberListOfPendingApplications}" var="item">
		numberListOfPendingApplications.push("${item}");
		</jstl:forEach>		
		
		class Ejes{
	        constructor(xAxe,yAxe){
	            this.x=xAxe;
	            this.y=yAxe;
	        }
	    }
		
		var pendingData = new Array();
		
	    for (var i = 0; i < dateListOfPendingApplications.length; i++) {
	        myAxe = new Ejes(dateListOfPendingApplications[i],numberListOfPendingApplications[i]);
	        pendingData.push(myAxe)
	    }
	    
	    <!-- Accepted -->
	    var dateListOfAcceptedApplications = new Array();
		<jstl:forEach items="${dateListOfAcceptedApplications}" var="item">
		dateListOfAcceptedApplications.push("${item}");
		</jstl:forEach>
		
		var numberListOfAcceptedApplications = new Array();
		<jstl:forEach items="${numberListOfAcceptedApplications}" var="item">
		numberListOfAcceptedApplications.push("${item}");
		</jstl:forEach>		
		
		var acceptedData = new Array();
		
	    for (var i = 0; i < dateListOfAcceptedApplications.length; i++) {
	        myAxe = new Ejes(dateListOfAcceptedApplications[i],numberListOfAcceptedApplications[i]);
	        acceptedData.push(myAxe)
	    }
	    
	    <!-- Rejected -->
	    var dateListOfRejectedApplications = new Array();
		<jstl:forEach items="${dateListOfRejectedApplications}" var="item">
		dateListOfRejectedApplications.push("${item}");
		</jstl:forEach>
		
		var numberListOfRejectedApplications = new Array();
		<jstl:forEach items="${numberListOfRejectedApplications}" var="item">
		numberListOfRejectedApplications.push("${item}");
		</jstl:forEach>		
		
		var rejectedData = new Array();
		
	    for (var i = 0; i < dateListOfRejectedApplications.length; i++) {
	        myAxe = new Ejes(dateListOfRejectedApplications[i],numberListOfRejectedApplications[i]);
	        rejectedData.push(myAxe)
	    }
		
	    
	    <!-- CHART -->
	    var timeFormat = 'YYYY-MM-DD';
	
	    var config = {
	        type:    'line',
	        data:    {
	            datasets: [
	                {
	                    label: "Pending",
	                    data: pendingData,
	                    fill: false,
	                    borderColor: 'blue'
	                },
	                {
	                    label: "Accepted",
	                    data: acceptedData,
	                    fill:  false,
	                    borderColor: 'green'
	                },
	                {
	                    label: "Rejected",
	                    data:  rejectedData,
	                    fill:  false,
	                    borderColor: 'red'
	                }
	            ]
	        },
	        options: {
	            responsive: true,
	            scales:     {
	                xAxes: [{
	
						type: 'time',
						time:{
						    unit: 'day',
	                        format: timeFormat
						},
						distribution: 'series'
							
	                }],
	                yAxes: [{
	                    ticks : {
									suggestedMin : 0,
									suggestedMax : 5
								}
	                }]
	            }
	        }
	    };
	
	    window.onload = function () {
	        var ctx       = document.getElementById("timeSeries").getContext("2d");
	        window.myLine = new Chart(ctx, config);
	    };

	</script>