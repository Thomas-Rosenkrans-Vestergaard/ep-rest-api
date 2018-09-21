function RestTable(tableElement, configuration) {

    this.tableElement = tableElement;
    this.configuration = configuration;

    var root = this;

    this.init = function () {

        root.tableElement.innerHTML = '';

        root.tableHead = root.createHead();
        root.tableBody = root.createBody();

        root.tableElement.appendChild(root.tableHead);
        root.tableElement.appendChild(root.tableBody);

        root.populate();
    };

    this.createHead = function () {
        var tableHead = document.createElement("thead");
        var tr = document.createElement("tr");

        tableHead.appendChild(tr);

        configuration.columns.forEach(function (column) {
            var th = document.createElement("th");
            th.innerText = column.name;
            if (column.class != undefined)
                th.setAttribute("class", column.class);

            tr.appendChild(th);
        });

        return tableHead;
    };

    this.createBody = function () {
        var body = document.createElement("tbody");

        return body;
    };

    this.clear = function () {
        root.tableBody.innerHTML = '';
    };

    this.refresh = function () {
        root.clear();
        root.populate();
    };

    this.populate = function () {
        var getter = root.configuration.getter;

        getter(function (rows) {
            root.appendRows(rows);
        });
    };

    this.appendRows = function (rows) {
        rows.forEach(function (row) {
            root.appendRow(row);
        });
    };

    this.appendRow = function (row) {

        var tr = document.createElement("tr");

        root.configuration.columns.forEach(function (column) {
            var td = document.createElement("td");
            td.innerText = column.value == undefined ? row[column.key] : column.value.call(row[column.key], row, column);
            if (column.class != undefined)
                td.setAttribute("class", column.class);

            tr.appendChild(td);
        });

        root.tableBody.appendChild(tr);
    };
}