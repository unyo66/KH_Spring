/**
 * 
 */
 
function frmChk(frm) {
    if(frm.id.value.length < 3){
        setMsg("아이디는 3글자 이상이어야 하오!!", frm.id);
        return false;
    } 
    else if(frm.pw.value.length < 3){
        setMsg("비번은 3글자 이상이어야 하오!!", frm.pw);
        return false;
    } 
    return true;
}
function setMsg(msg, el) {
    document.getElementById('msg').innerText = msg;
    el.select();
}