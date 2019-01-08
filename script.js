
$(function() {
	$("#inputText").val('');
});

shdEveryOtherRow = () => {
	
	$("#tbArea table tr ").each((index, item) => {

		if(index%2 == 0){
			$(this).attr("class","oddRow");
			console.log($(this).attr("class"));
		}
	});
}

shwDarkLnEveryTenRow = ()=>{
	$("tr").each((index) => {
		if((index+1)%10 == 0){
			$(this).attr("class","tenth");
			console.log($(this).attr("class"));
		}
	});
}

resetTable = () =>{
	$("tr").attr('class','');
}

generateTable = () => {
	
	if(!$.isNumeric($("#inputText").val())){
		alert("Invalid input");
		$("#inputText").val('');
		return true;
	}
	$("table").remove();
	$("#tbArea").find("button").remove();

	//get the table and create the row
	let table = $("<table></table>");
	let btnShdEveryOtherRow = $("<button onclick='shdEveryOtherRow()'>Shade Every Other Row</button>");
	let btnShwDarkLnEveryTenRow = $("<button onclick='shwDarkLnEveryTenRow()'>Show Dark Line Every Ten Row</button>");
	let resetTable = $("<button onclick='resetTable()'>Reset</button>");	
	let i;
	for(i=0;i<parseInt($("#inputText").val());i++){
		let row = $("<tr class=''></tr>");
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
	$("#inputText").val('');
	$("#inputText").focus();
}

generateTableEvt = (e) => {
	if(e.keyCode == 13)
		generateTable();
}

