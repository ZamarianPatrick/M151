function sortTable(table, n, clickedElement) {
    var rows, switching, i, x, y, shouldSwitch;
    switching = true;
    var alphabetic = clickedElement.getAttribute("alphabetic");
    while (switching) {
        switching = false;
        rows = table.rows;

        for (i = 1; i < (rows.length - 1); i++) {

            shouldSwitch = false;

            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];

            var xValue, yValue;

            if(x.getElementsByTagName("SELECT")[0] != null){
                var select = x.getElementsByTagName("SELECT")[0];
                xValue = select.options[select.selectedIndex].value;
            }else{
                xValue = x.innerHTML;
            }
            xValue = xValue.toLowerCase();

            if(y.getElementsByTagName("SELECT")[0] != null){
                var select = y.getElementsByTagName("SELECT")[0];
                yValue = select.options[select.selectedIndex].value;
            }else{
                yValue = y.innerHTML;
            }
            yValue = yValue.toLowerCase();
            if(isNaN(xValue) == false && isNaN(yValue) == false){
                xValue = parseFloat(xValue);
                yValue = parseFloat(yValue);
            }
            if(alphabetic != null){
                if (xValue > yValue) {
                    shouldSwitch = true;
                    break;
                }
            }else{
                if (xValue < yValue) {
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
        }
    }
    clickedElement.toggleAttribute("alphabetic");
}

function filter(tableId, element, n, selected) {
    var value;
    if(element.nodeName == "SELECT"){
        value = element.options[element.selectedIndex].value;
        if(value == "Alle"){
            value = "";
        }
    }else{
        value = element.value;
    }
    var table = document.getElementById(tableId);
    var rows = table.rows;
    for (var i = 1; i < (rows.length); i++) {
        var text;
        if(selected == false){
            text = rows[i].getElementsByTagName("TD")[n].innerHTML.toLowerCase();
        }else{
            var select = rows[i].getElementsByTagName("TD")[n].getElementsByTagName("SELECT")[0];
            text = select.options[select.selectedIndex].value.toLowerCase();
        }
        rows[i].style.display = text.includes(value.toLowerCase()) ? '' : 'none';
    }
}

function addArticlePackage(checkbox, hidden){
    var element = document.getElementById(hidden);
    element.toggleAttribute("hidden", !checkbox.checked);
}

function articles() {
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();

        var checkbox = $('table tbody input[type="checkbox"]');
        $("#selectAll").click(function(){
            if(this.checked){
                checkbox.each(function(){
                    if(this.parentElement.parentElement.parentElement.style.display != 'none') {
                        this.checked = true;
                    }
                });
            } else{
                checkbox.each(function(){
                    if(this.parentElement.parentElement.parentElement.style.display != 'none') {
                        this.checked = false;
                    }
                });
            }
        });
        checkbox.click(function(){
            if(!this.checked){
                $("#selectAll").prop("checked", false);
            }
        });
    });
}

function selectArticle(article){
    document.getElementById("submit-forward").toggleAttribute("disabled", false);
    var id = article.getAttribute("article-id");
    var price = article.getAttribute("article-price");
    document.getElementById("price-text").innerHTML= price;
    var otherArticles = article.parentElement.getElementsByTagName("DIV");
    for(var i = 0; i < otherArticles.length; i++){
        var otherArticle = otherArticles[i];
        otherArticle.classList.remove("selected-for-order");
        var otherId = otherArticle.getAttribute("article-id");
        if(otherId != null){
            document.getElementById("choose-text-" + otherId).toggleAttribute("hidden", true);
        }
    }
    article.classList.add("selected-for-order");
    document.getElementById("choose-" + id).checked = true;
    document.getElementById("choose-text-" + id).toggleAttribute("hidden", false);
}

function editArticle(row){
    var icon = row.getElementsByTagName("TD")[1].getAttribute("icon-name");
    var id = row.getElementsByTagName("TD")[2].innerHTML;
    var article = row.getElementsByTagName("TD")[3].innerHTML;
    var price = row.getElementsByTagName("TD")[4].innerHTML;
    var type = row.getElementsByTagName("TD")[5].innerHTML;
    var isPackage = false;
    document.getElementById("available-items-select-edit").toggleAttribute("hidden", true);
    if(type == 'Verpackung'){
        var itemsColumn = row.getElementsByTagName("TD")[6];
        document.getElementById("available-items-select-edit").toggleAttribute("hidden", false);
        $('#package-hidden-edit').multiSelect('deselect_all');
        var spans = itemsColumn.getElementsByTagName("SPAN");
        for(var i = 0, max = spans.length; i < max; i++) {
            $('#package-hidden-edit').multiSelect('select', spans[i].innerHTML);
        }
        isPackage = true;
    }
    document.getElementById("article-edit-id").value = id;
    document.getElementById("article-edit-icon").value = icon;
    document.getElementById("article-edit-name").value = article;
    document.getElementById("article-edit-price").value = price;
    document.getElementById("article-edit-isPackage").checked = isPackage;
}

function deleteArticle(id){
    var deletingItems = document.getElementById("deleting-items");
    while (deletingItems.firstChild) {
        deletingItems.removeChild(deletingItems.firstChild);
    }
    var node = document.createElement("input");
    node.setAttribute("type", "number");
    node.setAttribute("name", "ids");
    node.setAttribute("value", id);
    node.setAttribute("hidden", "");
    deletingItems.appendChild(node);
}

function deleteArticles(){
    var deletingItems = document.getElementById("deleting-items");
    while (deletingItems.firstChild) {
        deletingItems.removeChild(deletingItems.firstChild);
    }
    var checkboxes = $('table tbody input[type="checkbox"]');
    checkboxes.each(function() {
        if(this.checked){
            var row = this.parentElement.parentElement.parentElement;
            var el = row.getElementsByTagName("TD")[2];
            var id = el.innerHTML;
            var node = document.createElement("input");
            node.setAttribute("type", "number");
            node.setAttribute("name", "ids");
            node.setAttribute("value", id);
            node.setAttribute("hidden", "");
            deletingItems.appendChild(node);
        }
    });
}

function select(multiSelectId, toSelect){
    $(multiSelectId).multiSelect('select', toSelect);
}

function validateOrder(){
    if(document.getElementById('no-content') != null){
        document.getElementById('error-no-content').toggleAttribute("hidden", false);
        return false;
    }
    return true;
}

function showOrderDetail(elementId){
    var element = document.getElementById('detail-' + elementId);
    element.toggleAttribute("hidden");
}