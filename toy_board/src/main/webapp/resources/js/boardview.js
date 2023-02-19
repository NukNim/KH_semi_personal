/**
 * 게시판 상세 페이지 
 */
 
 function intoHome(){
	location.href="list";
}
 function intoList(p){
	location.href="list?p="+p;
}

function gotoWrite(){
	location.href="write";
}

function goUpdate(id, p){
	location.href="update?id="+id+"&p="+p;
}
function goDelete(id){
	location.href="delete?id="+id;
}

 