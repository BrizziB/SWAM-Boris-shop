"use strict";
var Order = (function () {
    function Order() {
    }
    return Order;
}());
exports.Order = Order;
var Status;
(function (Status) {
    Status[Status["REQUESTED"] = 1] = "REQUESTED";
    Status[Status["PAYED"] = 2] = "PAYED";
    Status[Status["IN_PREPARATION"] = 3] = "IN_PREPARATION";
    Status[Status["SENT"] = 4] = "SENT";
    Status[Status["RECEIVED"] = 5] = "RECEIVED";
})(Status = exports.Status || (exports.Status = {}));
//# sourceMappingURL=Order.js.map