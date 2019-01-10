
$(function() {
	$("#inputNumber").val('');
});

shdEveryOtherRow = (evt) => {
	
	$("tr").each(function(i){
		if(i%2 == 0)
			$(this).attr("class","oddRow");
	});
}

shwDarkLnEveryTenRow = ()=>{
	$("tr").each(function(index) {
		if((index+1)%10 == 0)
			$(this).attr("class","tenth");
	});
}

resetTable = () =>{
	$("tr").attr('class','');
}

generateTable = () => {
	
	if(parseInt($("#inputNumber").val()) < parseInt($("#inputNumber").attr('min')) 
	|| parseInt($("#inputNumber").val())>parseInt($("#inputNumber").attr('max'))){
		alert("Invalid input");
		$("#inputNumber").val('');
		return true;
	}
	$("table").remove();
	$("#tbArea").find("button").remove();

	//get the table and create the row
	let table = $("<table></table>");
	let btnShdEveryOtherRow = $("<button onclick='shdEveryOtherRow(event)'>Shade Every Other Row</button>");
	let btnShwDarkLnEveryTenRow = $("<button onclick='shwDarkLnEveryTenRow()'>Show Dark Line Every Ten Row</button>");
	let resetTable = $("<button onclick='resetTable()'>Reset</button>");	
	let i,size;
	for(i=0,size=parseInt($("#inputNumber").val());i<size;i++){
		let row = $("<tr ></tr>");
		let left = $("<td></td>");
		//now the right hand side elements
		let right = $("<td></td>");
		//set attribute and wrap it up and get it to the left
		left.text(i+1);
		right.text("God of Code");

		//stack them together and wrap them up
		row.append(left);
		row.append(right);
		table.append(row);
	}
	$("#tbArea").append(btnShdEveryOtherRow);
	$("#tbArea").append(btnShwDarkLnEveryTenRow);
	$("#tbArea").append(resetTable);
	$("#tbArea").append(table);
	$("#inputNumber").val('');
	$("#inputNumber").focus();
}

generateTableEvt = (e) => {
	if(e.keyCode == 13)
		generateTable();
}

