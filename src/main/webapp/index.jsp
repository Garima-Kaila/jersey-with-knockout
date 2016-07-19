<html>
<body>

 <h2>Jersey RESTful Web Application!</h2>
        <p><a href="webapi/orders">Jersey resource</a>
        <p>Visit <a href="http://jersey.java.net">Project Jersey website</a>
            for more information on Jersey!   <br><br><br>

        <form action="webapi/orders/orderList" method="POST"/>
        OrderID :  <input type="number" name="orderid"/><br><br>
        Item ID: <input type="number" name="itemid"/><br><br>
        Amount:  <input type="number" name="amount"/><br><br>
        Status: <input type="text" name="status"/><br><br>
        <input type="Submit" value="Submit"/><br><br><br>


        <script type = "text/javascript"
        src = "http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/knockout/knockout-2.2.1.js"></script>

        <script>
            $(document).ready(function () {
                $("#btnGetOrders").click(function () {
                    $.ajax({
                        type: "GET",
                        url: "webapi/orders",
                        datatype:"json",
                        success: function (data, status) {

                             alert("sucessfully get data"+" "+data);
                            if (data) {
                                var len = data.length;
                                var txt = "";
                                if (len > 0) {
                                    for (var i = 0; i < len; i++) {
                                        if (data[i].orderId && data[i].itemId && data[i].amount && data[i].status) {
                                            txt += "<tr><td>" + data[i].orderId + "</td><td>" + data[i].itemId + "</td><td>" + data[i].amount + "</td><td>" + data[i].status + "</td></tr>"
                                        }
                                    }
                                    if (txt != "") {
                                        $("#table").append(txt).removeClass("hidden");
                                    }
                                }
                            }
                        },
                        error: function (xhr) {
                            alert(xhr.responseText);
                        }

                    });
                    return false;
                });
            });
            $(function () {
                $("#btnAddOrder").click(function () {
                    var jsonObj = {"orderId": $("#orderid").val(),
                        "itemId": $("#itemid").val(),
                        "amount": $("#amount").val(),
                        "status": $("#status").val()};

                    $.ajax({
                        type: "POST",
                        url: "webapi/orders/orderdata",
                        data: JSON.stringify(jsonObj),
                        contentType: "application/json",
                        dataType: "json",
                        //processData: true,
                        success: function (status, data) {
                            alert("success...");
                        },
                        error: function (xhr) {
                            alert(xhr.responseText);
                        }

                    });
                    return false;
                });
            });


        </script>


        <div id="div1"><h2>jQuery Text</h2></div>

        <input type="button" id="btnGetOrders" value="Get Order Data"></input>

        <table id="table" class="hidden" >
            <tr>
                <th>Order Id</th>
                <th>Item Id</th>
                <th>Amount</th>
                <th>Status</th>
            </tr>
        </table  <br> <br> <br> <br>


        <h3>Post Order Data</h3>
        Order ID:  <input type="number" data-bind="value:orderId" name="orderid" id="orderid" /><br><br>
               Item Id:  <input type="number" data-bind="value:itemId" name="itemid" id="itemid" /><br><br>
               Amount: <input type="number" data-bind="value:amount" name="amount" id="amount" /><br><br>
               Status:  <input type="text" data-bind="value:status"   name="status" id="status" /><br><br>
               <input type="button" name="Submit" data-bind="click: addOrder" value="Submit" />



<br><br>
  <table>
      <thead>
          <tr><th>orderId</th>
          <th>itemId</th>
          <th>amount</th>
          <th>status</th>
          </tr>
      </thead>
      <tbody data-bind="foreach: orders">
          <tr>
              <td data-bind="text: orderId"></td>
              <td data-bind="text: itemId"></td>
              <td data-bind="text: amount"></td>
              <td data-bind="text: status"></td>
          </tr>
      </tbody>
  </table>
<script>


var myViewModel = {
    orderId: ko.observable(),
    itemId: ko.observable(),
    amount: ko.observable(),
    status: ko.observable(),
     numberOfClicks : ko.observable(0),
            addOrder : function() {
            postOrderViewMode({
                                                orderId: this.orderId(),
                                                  itemId:this.itemId(),
                                                  amount:this.amount(),
                                                  status:this.status()
                                              });

            },
             orders: ko.observableArray([])
};
$(document).ready(function () {
ko.applyBindings(myViewModel);
getOrders();
});

function getOrders(){
 $.ajax({
                        type: "GET",
                        url: "webapi/orders",
                        datatype:"json",
                        success: function (data, status) {

                             alert("sucessfully get data"+" "+data);
                            if (data && data.length>0) {
                            data.map(function(order){
                            myViewModel.orders.push(order);
                            });
                            }
                        },
                        error: function (xhr) {
                            alert(xhr.responseText);
                        }

                    });
}

function postOrderViewMode(vm){
 $.ajax({
                        type: "POST",
                        url: "webapi/orders/orderdata",
                        data: ko.toJSON(vm),
                        contentType: "application/json",
                        dataType: "json",
                        //processData: true,
                        success: function (data) {
                            myViewModel.orders.push(data);
                        },
                        error: function (xhr) {
                            alert(xhr.responseText);
                        }

                    });
}

</script>
</body>
</html>
