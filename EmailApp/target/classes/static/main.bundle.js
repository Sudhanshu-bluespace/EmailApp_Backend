webpackJsonp([0],{

/***/ "../../../../../src async recursive":
/***/ (function(module, exports) {

function webpackEmptyContext(req) {
	throw new Error("Cannot find module '" + req + "'.");
}
webpackEmptyContext.keys = function() { return []; };
webpackEmptyContext.resolve = webpackEmptyContext;
module.exports = webpackEmptyContext;
webpackEmptyContext.id = "../../../../../src async recursive";

/***/ }),

/***/ "../../../../../src/app/analytics/analytics.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../node_modules/css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, ".centered {\r\n  position: fixed;\r\n  top: 50%;\r\n  left: 50%;\r\n  -webkit-transform: translate(-80%, -50%);\r\n          transform: translate(-80%, -50%);\r\n}\r\n\r\n.divtabs, .barChartTabs {\r\n\t\r\n\tpadding-left:8%;\r\n\twidth:80%;\r\n\theight:100%;\r\n}\r\n\r\n.tab.active {\r\n\tbackground:blue;\r\n}\r\n\r\nmd-card {\r\nbox-shadow: -4px 10px 25px -6px rgba(0,0,0,0.75);\r\nmargin-top:2vh;\r\nbackground:white;\r\nborder-radius: 12px 12px 12px 12px;\r\n-moz-border-radius: 12px 12px 12px 12px;\r\n-webkit-border-radius: 12px 12px 12px 12px;\r\nborder: 0px solid #000000;\r\nmin-height:25vh;\r\nwidth:105%;\r\n}\r\n\r\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/analytics/analytics.component.html":
/***/ (function(module, exports) {

module.exports = "<!--form (ngSubmit)=\"onSubmit()\" autocomplete=\"off\" novalidate #loginForm=\"ngForm\"-->\r\n<script src=\"https://www.gstatic.com/charts/loader.js\"></script>\r\n<script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script>\r\n<script type=\"text/javascript\">        \r\n  google.load('visualization', '1.0', {\r\n    'packages': ['corechart']\r\n  });\r\n  \r\n</script>\r\n  <div class=\"col-xs-1 col-sm-3 col-md-4 col-lg-4 col-xl-4\"></div>\r\n  <div class=\"barChartTabs\" *ngIf=\"authorizationService.isUserSuperAdmin()\">\r\n      <md-card class=\"app-analytics-display-section\">\r\n      <md-card-title>Company Wise Registration Statistics</md-card-title>\r\n      <md-card-content>\r\n\t\t<google-chart [data]='regColumnChartOptions'></google-chart>\r\n      </md-card-content>\r\n      <md-card-actions>\r\n      </md-card-actions>\r\n    </md-card>\r\n  </div>\r\n  <div class=\"divtabs\">\r\n    <md-card class=\"app-analytics-display-section\">\r\n      <md-card-title>Campaign Wise Performance Statistics</md-card-title>\r\n      <md-card-content>\r\n\t  <p>This view allows you to analyze the campaign statistics completed by you till date. \r\n\t\tIt has a stacked column chart with Total Reach, Clicks and Unsubscribes for each campaign.\r\n\t  </p>\r\n\t\t<google-chart [data]='columnChartOptions'></google-chart>\r\n      </md-card-content>\r\n      <md-card-actions>\r\n      </md-card-actions>\r\n    </md-card>\r\n  </div>\r\n  <div class=\"barChartTabs\">\r\n      <md-card class=\"app-analytics-display-section\">\r\n      <md-card-title>Group Wise Unsubscription</md-card-title>\r\n      <md-card-content>\r\n\t  <p>This is a view of how your contacts have opted for unsubscribing from your campaigns. It shows a bar chart for number of unsubscribes per group that you have created. \r\n\t  </p>\r\n\t\t<google-chart [data]='barChartOptions'></google-chart>\r\n      </md-card-content>\r\n      <md-card-actions>\r\n      </md-card-actions>\r\n    </md-card>\r\n  </div>\r\n<!--/form-->"

/***/ }),

/***/ "../../../../../src/app/analytics/analytics.root.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__analytics_analytics_service__ = __webpack_require__("../../../../../src/app/analytics/analytics.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__core_global_service__ = __webpack_require__("../../../../../src/app/core/global.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__core_authorization_service__ = __webpack_require__("../../../../../src/app/core/authorization.service.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AnalyticsComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var AnalyticsComponent = (function () {
    function AnalyticsComponent(AnalyticsService, router, globalService, authorizationService) {
        this.AnalyticsService = AnalyticsService;
        this.router = router;
        this.globalService = globalService;
        this.authorizationService = authorizationService;
        this.regColumnChartOptions = {
            chartType: 'ColumnChart',
            dataTable: [
                [{ label: 'Company Name', type: 'string' }, { label: 'Nmber of Approved Registrations', type: 'number' }, { label: 'Number of Pending Registrations', type: 'number' }]
            ],
            options: {
                title: 'Company Wise Registrations',
                animation: {
                    duration: 1000,
                    easing: 'out',
                    startup: true
                },
                bar: {
                    groupWidth: '40'
                },
                vAxis: {
                    title: 'Count'
                },
                hAxis: {
                    title: 'Company Name'
                },
                seriesType: 'bars',
                isStacked: true,
                width: '95%',
                height: '350'
            }
        };
        this.columnChartOptions = {
            chartType: 'ColumnChart',
            dataTable: [
                [{ label: 'Campaign Name', type: 'string' }, { label: 'Total Reach', type: 'number' }, { label: 'Total Clicks', type: 'number' }, { label: 'Unsubscribes', type: 'number' }]
            ],
            options: {
                title: 'Campaign Wise Performance Summary',
                height: '350',
                bar: {
                    groupWidth: '40'
                },
                vAxis: {
                    title: 'Count'
                },
                hAxis: {
                    title: 'Campaign Name'
                },
                seriesType: 'bars',
                isStacked: true,
                animation: {
                    duration: 1000,
                    easing: 'out',
                    startup: true
                },
                width: '95%'
            }
        };
        this.comboChartOptions = {
            chartType: 'ComboChart',
            dataTable: [
                ['Campaign Name', 'Total Reach', 'Total Clicks', 'Unsubscribes']
            ],
            options: {
                title: 'Campaign Wise Performance Summary',
                height: '350',
                vAxis: { title: 'Count' },
                hAxis: { title: 'Campaign Name' },
                seriesType: 'bars',
                series: { 5: { type: 'line' } },
                animation: {
                    duration: 1000,
                    easing: 'out',
                    startup: true
                },
                width: '95%',
                chartArea: { width: '65%', right: '15%', top: '1%', height: '85%' },
            }
        };
        this.pieChartOptions = {
            chartType: 'PieChart',
            dataTable: [
                ['Metrics', 'Percentage'],
                ['Sent', 11],
                ['Reach', 2],
                ['Clicks', 2],
                ['Failure', 2],
                ['Bounce', 7]
            ],
            options: {
                title: 'Tasks',
                animation: {
                    duration: 1000,
                    easing: 'out',
                    startup: true
                },
                is3D: true,
                width: '95%',
                height: '350'
            }
        };
        this.barChartOptions = {
            chartType: 'BarChart',
            dataTable: [
                [{ label: 'Group Name', type: 'string' }, { label: 'Count', type: 'number' }]
            ],
            options: {
                width: '95%',
                height: '350',
                chartArea: { width: '65%', right: '15%', top: '1%' },
                bar: {
                    groupWidth: '40'
                },
                animation: {
                    duration: 1000,
                    easing: 'out',
                    startup: true
                },
                hAxis: {
                    title: 'Count',
                    minValue: 0,
                    textStyle: {
                        bold: true,
                        fontSize: 11,
                        color: '#4d4d4d'
                    },
                    titleTextStyle: {
                        bold: true,
                        fontSize: 12,
                        color: '#4d4d4d'
                    }
                },
                vAxis: {
                    title: 'Group Names',
                    textStyle: {
                        fontSize: 11,
                        bold: true,
                    },
                    titleTextStyle: {
                        fontSize: 14,
                        bold: true,
                        color: '#848484'
                    }
                }
            }
        };
        var user = this.globalService.loggedInUser.loggedInUserName;
        this.campaignWisePerformance(user);
        this.groupWiseSunsubscription(user);
        this.getCompanyWiseRegistrationStats();
    }
    AnalyticsComponent.prototype.campaignWisePerformance = function (username) {
        var _this = this;
        this.AnalyticsService.campaignWisePerformanceSummary(username)
            .subscribe(function (data) {
            _this.campaignWisePerformanceSummaryResponse = data;
            /*	for (let entry of this.campaignWisePerformanceSummaryResponse)
                {
                    console.log(entry.campaignName+" | "+entry.totalReach); // 1, "string", false
                }*/
            //console.log("response : "+this.summary.unsubscribes+" | "+this.summary.reach+" | "+this.summary.clicks);
            _this.myClickCampaignWisePerformance();
            //this.loadData("Sent on "+this.summary.sentOn,this.summary.subject,this.summary.clickPercentage,this.summary.unsubscribePercentage);
        }, function (error) {
        });
    };
    ;
    AnalyticsComponent.prototype.getCompanyWiseRegistrationStats = function () {
        var _this = this;
        this.AnalyticsService.companyWiseRegistrationStats()
            .subscribe(function (data) {
            _this.companyWiseRegistrationSummary = data;
            /*for (let entry of this.companyWiseRegistrationSummary)
            {
                console.log(entry.companyName+" | "+entry.approvedCount+" | "+entry.pendingCount); // 1, "string", false
            }*/
            //console.log("response : "+this.summary.unsubscribes+" | "+this.summary.reach+" | "+this.summary.clicks);
            _this.myClickCompanyWiseRegistration();
            //this.loadData("Sent on "+this.summary.sentOn,this.summary.subject,this.summary.clickPercentage,this.summary.unsubscribePercentage);
        }, function (error) {
        });
    };
    ;
    AnalyticsComponent.prototype.groupWiseSunsubscription = function (username) {
        var _this = this;
        this.AnalyticsService.groupWiseUnsubscription(username)
            .subscribe(function (data) {
            _this.groupWiseUnsubscriptionResponse = data;
            /*	for (let entry of this.groupWiseUnsubscriptionResponse)
                {
                    console.log(entry.groupName+" | "+entry.unsubscribed); // 1, "string", false
                }*/
            //console.log("response : "+this.summary.unsubscribes+" | "+this.summary.reach+" | "+this.summary.clicks);
            _this.myClickGroupWiseUnsubscribes();
            //this.loadData("Sent on "+this.summary.sentOn,this.summary.subject,this.summary.clickPercentage,this.summary.unsubscribePercentage);
        }, function (error) {
        });
    };
    AnalyticsComponent.prototype.myClickGroupWiseUnsubscribes = function () {
        // forces a reference update (otherwise angular doesn't detect the change)
        this.barChartOptions = Object.create(this.barChartOptions);
        for (var _i = 0, _a = this.groupWiseUnsubscriptionResponse; _i < _a.length; _i++) {
            var entry = _a[_i];
            //console.log("Setting values for : "+entry.groupName+" | "+entry.unsubscribed);	
            this.barChartOptions.dataTable.push([entry.groupName, entry.unsubscribed]);
        }
    };
    AnalyticsComponent.prototype.myClickCompanyWiseRegistration = function () {
        // forces a reference update (otherwise angular doesn't detect the change)
        this.regColumnChartOptions = Object.create(this.regColumnChartOptions);
        for (var _i = 0, _a = this.companyWiseRegistrationSummary; _i < _a.length; _i++) {
            var entry = _a[_i];
            //console.log("Setting values for : "+entry.companyName);	
            this.regColumnChartOptions.dataTable.push([entry.companyName, entry.approvedCount, entry.pendingCount]);
        }
    };
    AnalyticsComponent.prototype.myClickCampaignWisePerformance = function () {
        // forces a reference update (otherwise angular doesn't detect the change)
        //console.log("Combo chart values : "+this.columnChartOptions.dataTable[0][0]+" | "+this.columnChartOptions.dataTable[0][1]);
        this.columnChartOptions = Object.create(this.columnChartOptions);
        for (var _i = 0, _a = this.campaignWisePerformanceSummaryResponse; _i < _a.length; _i++) {
            var entry = _a[_i];
            //console.log("Setting values for : "+entry.subject+" | "+entry.totalReach+" | "+entry.clicks+" | "+entry.unsubscribes);	
            this.columnChartOptions.dataTable.push([entry.subject, entry.totalReach, entry.clicks, entry.unsubscribes]);
        }
    };
    return AnalyticsComponent;
}());
AnalyticsComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'analytics',
        template: __webpack_require__("../../../../../src/app/analytics/analytics.component.html"),
        styles: [__webpack_require__("../../../../../src/app/analytics/analytics.component.css")],
        providers: [__WEBPACK_IMPORTED_MODULE_2__analytics_analytics_service__["a" /* AnalyticsService */]]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_2__analytics_analytics_service__["a" /* AnalyticsService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__analytics_analytics_service__["a" /* AnalyticsService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["Router"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["Router"]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_3__core_global_service__["a" /* GlobalService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__core_global_service__["a" /* GlobalService */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_4__core_authorization_service__["a" /* AuthorizationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__core_authorization_service__["a" /* AuthorizationService */]) === "function" && _d || Object])
], AnalyticsComponent);

var _a, _b, _c, _d;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/analytics.root.component.js.map

/***/ }),

/***/ "../../../../../src/app/analytics/analytics.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__("../../../http/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__ = __webpack_require__("../../../../rxjs/Observable.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AnalyticsService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var AnalyticsService = (function () {
    function AnalyticsService(http) {
        this.http = http;
    }
    AnalyticsService.prototype.getJobStatusData = function (userName) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* RequestOptions */]({ headers: headers });
        var urlSearchParams = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["e" /* URLSearchParams */]();
        urlSearchParams.append('userName', userName);
        var body = urlSearchParams.toString();
        return this.http.post('analytics/getJobStatusData', body, options)
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    AnalyticsService.prototype.getBlockedContacts = function () {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* RequestOptions */]({ headers: headers });
        //let urlSearchParams = new URLSearchParams();
        //urlSearchParams.append('userName', userName);
        //let body = urlSearchParams.toString();
        return this.http.post('analytics/getBlockedContacts', options)
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    AnalyticsService.prototype.cancelJob = function (batchId, requestId) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* RequestOptions */]({ headers: headers });
        var urlSearchParams = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["e" /* URLSearchParams */]();
        urlSearchParams.append('batchId', batchId.toString());
        urlSearchParams.append('requestId', requestId);
        var body = urlSearchParams.toString();
        return this.http.post('analytics/cancelJob', body, options)
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    AnalyticsService.prototype.recentChartSummary = function (userName) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        //console.log("dashboard for "+userName);
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* RequestOptions */]({ headers: headers });
        var urlSearchParams = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["e" /* URLSearchParams */]();
        urlSearchParams.append('userName', userName);
        var body = urlSearchParams.toString();
        return this.http.post('analytics/recentSummary', body, options)
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    AnalyticsService.prototype.recentUnsubscribes = function (age, isAdmin) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* RequestOptions */]({ headers: headers });
        var urlSearchParams = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["e" /* URLSearchParams */]();
        urlSearchParams.append('age', age.toString());
        urlSearchParams.append('isAdmin', isAdmin);
        var body = urlSearchParams.toString();
        return this.http.post('analytics/getRecentUnsubscribes', body, options)
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    AnalyticsService.prototype.recentUnsubscribedCount = function (age, isAdmin) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* RequestOptions */]({ headers: headers });
        var urlSearchParams = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["e" /* URLSearchParams */]();
        urlSearchParams.append('age', age.toString());
        urlSearchParams.append('isAdmin', isAdmin);
        var body = urlSearchParams.toString();
        return this.http.post('analytics/getRecentUnsubscribedCount', body, options)
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    AnalyticsService.prototype.companyWiseRegistrationStats = function () {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* RequestOptions */]({ headers: headers });
        return this.http.get('analytics/getCompanyWiseRegistrationStats', options)
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    AnalyticsService.prototype.campaignWisePerformanceSummary = function (userName) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        //console.log("campaign wise summary for "+userName);
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* RequestOptions */]({ headers: headers });
        var urlSearchParams = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["e" /* URLSearchParams */]();
        urlSearchParams.append('userName', userName);
        var body = urlSearchParams.toString();
        return this.http.post('analytics/campaignWisePerformance', body, options)
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    AnalyticsService.prototype.groupWiseUnsubscription = function (userName) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        //console.log("groupwise unsubscription for "+userName);
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* RequestOptions */]({ headers: headers });
        var urlSearchParams = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["e" /* URLSearchParams */]();
        urlSearchParams.append('userName', userName);
        var body = urlSearchParams.toString();
        return this.http.post('analytics/groupWiseUnsubscription', body, options)
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
        ;
    };
    AnalyticsService.prototype.extractData = function (res) {
        var body = res.json();
        return body.data || {};
    };
    AnalyticsService.prototype.handleError = function (error) {
        // In a real world app, we might use a remote logging infrastructure
        //console.log(error);
        var errMsg;
        if (error instanceof __WEBPACK_IMPORTED_MODULE_1__angular_http__["f" /* Response */]) {
            var body = error.json() || '';
            var err = body.error || JSON.stringify(body);
            errMsg = error.status + " - " + err;
        }
        else {
            errMsg = error.message ? error.message : error.toString();
        }
        //console.error(errMsg);
        return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].throw(errMsg);
    };
    return AnalyticsService;
}());
AnalyticsService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */]) === "function" && _a || Object])
], AnalyticsService);

var _a;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/analytics.service.js.map

/***/ }),

/***/ "../../../../../src/app/analytics/tab.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Tab; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var Tab = (function () {
    function Tab() {
        this.active = false;
        this.columnChartOptions = {
            chartType: 'ColumnChart',
            dataTable: [
                ['Country', 'Performance', 'Profits'],
                ['Germany', 700, 1200],
                ['USA', 300, 600],
                ['Brazil', 400, 500],
                ['Canada', 500, 1000],
                ['France', 600, 1100],
                ['RU', 800, 1000]
            ],
            options: { title: 'Countries' }
        };
        this.pieChartOptions = {
            chartType: 'PieChart',
            dataTable: [
                ['Task', 'Hours per Day'],
                ['Work', 11],
                ['Eat', 2],
                ['Commute', 2],
                ['Watch TV', 2],
                ['Sleep', 7]
            ],
            options: {
                title: 'Total Summary',
                is3D: true,
                height: 300,
                chartArea: {
                    left: 1
                }
            }
        };
        this.gaugeChartOptions = {
            chartType: 'Gauge',
            dataTable: [
                ['Label', 'Value'],
                ['Value', 1.78]
            ],
            options: {
                animation: { easing: 'out' },
                width: 150, height: 150,
                greenFrom: 1, greenTo: 4,
                minorTicks: 5,
                min: 0, max: 5,
                majorTicks: ['0', '1', '2', '3', '4', '5'],
                greenColor: '#d0e9c6'
            }
        };
        this.scatterChartOptions = {
            chartType: 'ScatterChart',
            dataTable: [
                ['Age', 'Weight'],
                [8, 12],
                [4, 5.5],
                [11, 14],
                [4, 5],
                [3, 3.5],
                [6.5, 7]
            ],
            options: {
                title: 'Age vs. Weight comparison',
                hAxis: { title: 'Age', minValue: 0, maxValue: 15 },
                vAxis: { title: 'Weight', minValue: 0, maxValue: 15 },
                legend: 'none'
            }
        };
        this.timelineChartOptions = {
            chartType: 'Timeline',
            dataTable: [
                ['Name', 'From', 'To'],
                ['Washington', new Date(1789, 3, 30), new Date(1797, 2, 4)],
                ['Adams', new Date(1797, 2, 4), new Date(1801, 2, 4)],
                ['Jefferson', new Date(1801, 2, 4), new Date(1809, 2, 4)]
            ]
        };
        this.lineChartOptions = {
            chartType: 'LineChart',
            dataTable: [
                ['Year', 'Sales', 'Expenses'],
                ['2004', 1000, 400],
                ['2005', 1170, 460],
                ['2006', 660, 1120],
                ['2007', 1030, 540]
            ],
            options: { title: 'Company Performance' }
        };
    }
    Tab.prototype.myClick = function () {
        // forces a reference update (otherwise angular doesn't detect the change)
        this.columnChartOptions = Object.create(this.columnChartOptions);
        for (var i = 1; i < 7; i++) {
            this.columnChartOptions.dataTable[i][1] = Math.round(Math.random() * 1000);
            this.columnChartOptions.dataTable[i][2] = Math.round(Math.random() * 1000);
        }
    };
    Tab.prototype.ready = function (event) {
        console.log(event.message);
    };
    Tab.prototype.error = function (event) {
        console.error(event);
    };
    Tab.prototype.select = function (event) {
        this.selectEvent = event;
    };
    return Tab;
}());
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])('tabTitle'),
    __metadata("design:type", String)
], Tab.prototype, "title", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Input"])(),
    __metadata("design:type", Object)
], Tab.prototype, "active", void 0);
Tab = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'tab',
        styles: ["\n    .pane{\n      padding: 2em;\n    }\n  "],
        template: "\n    <div [hidden]=\"!active\" class=\"pane\">\n\t\t<google-chart [data]='pieChartOptions'></google-chart>\n    </div>\n  "
    })
], Tab);

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/tab.component.js.map

/***/ }),

/***/ "../../../../../src/app/analytics/tabs.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__tab_component__ = __webpack_require__("../../../../../src/app/analytics/tab.component.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Tabs; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var Tabs = (function () {
    function Tabs() {
    }
    // contentChildren are set
    Tabs.prototype.ngAfterContentInit = function () {
        // get all active tabs
        var activeTabs = this.tabs.filter(function (tab) { return tab.active; });
        // if there is no active tab set, activate the first
        if (activeTabs.length === 0) {
            this.selectTab(this.tabs.first);
        }
    };
    Tabs.prototype.selectTab = function (tab) {
        // deactivate all tabs
        this.tabs.toArray().forEach(function (tab) { return tab.active = false; });
        // activate the tab the user has clicked on.
        tab.active = true;
    };
    return Tabs;
}());
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["ContentChildren"])(__WEBPACK_IMPORTED_MODULE_1__tab_component__["a" /* Tab */]),
    __metadata("design:type", typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_0__angular_core__["QueryList"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_0__angular_core__["QueryList"]) === "function" && _a || Object)
], Tabs.prototype, "tabs", void 0);
Tabs = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'tabs',
        template: "\n    <ul class=\"nav nav-tabs\">\n      <li *ngFor=\"let tab of tabs\">\n        <h5 (click)=\"selectTab(tab)\" [class.active]=\"tab.active\">{{tab.title}}</h5>\n      </li>\n    </ul>\n    <ng-content></ng-content>\n  ",
        styles: ["\n    ul {\n\t\tbackground:#19334d;\n\t\tbox-shadow: 4px 4px 9px grey;\n\t\t/*border: 2px solid #19334d;\n\t\tborder-radius: 5px;*/\n\t\t\n\t}\n    li h5 {\n\t\tcursor:pointer; cursor:hand;\n\t}\n    ul li h5{\n      padding: 0.5em 2em;\n\t  border-right: 1px solid white;\n\t  color:white;\n    }\n  "],
    })
], Tabs);

var _a;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/tabs.component.js.map

/***/ }),

/***/ "../../../../../src/app/app-routing.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__default__ = __webpack_require__("../../../../../src/app/default/index.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__security_login__ = __webpack_require__("../../../../../src/app/security/login/index.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__dashboard__ = __webpack_require__("../../../../../src/app/dashboard/index.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__security_user_role__ = __webpack_require__("../../../../../src/app/security/user-role/index.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__security_user_group__ = __webpack_require__("../../../../../src/app/security/user-group/index.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__security_user_account__ = __webpack_require__("../../../../../src/app/security/user-account/index.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__email_group_group_component__ = __webpack_require__("../../../../../src/app/email/group/group.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__email_contact_contact_component__ = __webpack_require__("../../../../../src/app/email/contact/contact.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__email_email_email_component__ = __webpack_require__("../../../../../src/app/email/email/email.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__email_server_emailserver_component__ = __webpack_require__("../../../../../src/app/email/server/emailserver.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__security_change_password__ = __webpack_require__("../../../../../src/app/security/change-password/index.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__analytics_analytics_root_component__ = __webpack_require__("../../../../../src/app/analytics/analytics.root.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14__email_contact_fileupload_component__ = __webpack_require__("../../../../../src/app/email/contact/fileupload.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15__security_registration__ = __webpack_require__("../../../../../src/app/security/registration/index.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_16__security_account_approval_accountapproval_component__ = __webpack_require__("../../../../../src/app/security/account-approval/accountapproval.component.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return EmailAppRoutingModule; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};

















var routes = [
    { path: '', component: __WEBPACK_IMPORTED_MODULE_2__default__["a" /* DefaultComponent */] },
    { path: 'send_email', component: __WEBPACK_IMPORTED_MODULE_10__email_email_email_component__["a" /* EmailComponent */] },
    { path: 'contacts', component: __WEBPACK_IMPORTED_MODULE_9__email_contact_contact_component__["a" /* ContactComponent */] },
    { path: 'groups', component: __WEBPACK_IMPORTED_MODULE_8__email_group_group_component__["a" /* GroupComponent */] },
    { path: 'servers', component: __WEBPACK_IMPORTED_MODULE_11__email_server_emailserver_component__["a" /* EmailServerComponent */] },
    { path: 'user_roles', component: __WEBPACK_IMPORTED_MODULE_5__security_user_role__["a" /* UserRoleComponent */] },
    { path: 'user_accounts', component: __WEBPACK_IMPORTED_MODULE_7__security_user_account__["a" /* UserAccountComponent */] },
    { path: 'user_groups', component: __WEBPACK_IMPORTED_MODULE_6__security_user_group__["a" /* UserGroupComponent */] },
    { path: 'dashboard', component: __WEBPACK_IMPORTED_MODULE_4__dashboard__["a" /* DashboardComponent */] },
    { path: 'appLogin', component: __WEBPACK_IMPORTED_MODULE_3__security_login__["a" /* LoginComponent */] },
    { path: 'changePassword', component: __WEBPACK_IMPORTED_MODULE_12__security_change_password__["a" /* ChangePasswordComponent */] },
    { path: 'analytics', component: __WEBPACK_IMPORTED_MODULE_13__analytics_analytics_root_component__["a" /* AnalyticsComponent */] },
    { path: 'bulk_upload', component: __WEBPACK_IMPORTED_MODULE_14__email_contact_fileupload_component__["a" /* FileUploadComponent */] },
    { path: 'appRegister', component: __WEBPACK_IMPORTED_MODULE_15__security_registration__["a" /* RegistrationComponent */] },
    { path: 'account_approval', component: __WEBPACK_IMPORTED_MODULE_16__security_account_approval_accountapproval_component__["a" /* AccountApprovalComponent */] }
];
var EmailAppRoutingModule = (function () {
    function EmailAppRoutingModule() {
    }
    return EmailAppRoutingModule;
}());
EmailAppRoutingModule = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["NgModule"])({
        imports: [__WEBPACK_IMPORTED_MODULE_1__angular_router__["RouterModule"].forRoot(routes, { useHash: true })],
        exports: [__WEBPACK_IMPORTED_MODULE_1__angular_router__["RouterModule"]],
        providers: []
    })
], EmailAppRoutingModule);

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/app-routing.module.js.map

/***/ }),

/***/ "../../../../../src/app/app.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};

var AppComponent = (function () {
    function AppComponent() {
    }
    return AppComponent;
}());
AppComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-root',
        template: '<home-comp>loading</home-comp>'
        //template: '<upload></upload>'
    })
], AppComponent);

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/app.component.js.map

/***/ }),

/***/ "../../../../../src/app/app.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__ = __webpack_require__("../../../platform-browser/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_forms__ = __webpack_require__("../../../forms/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_http__ = __webpack_require__("../../../http/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__angular_material__ = __webpack_require__("../../../material/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__app_component__ = __webpack_require__("../../../../../src/app/app.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__app_routing_module__ = __webpack_require__("../../../../../src/app/app-routing.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__home_home_component__ = __webpack_require__("../../../../../src/app/home/home.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__rxjs_extensions__ = __webpack_require__("../../../../../src/app/rxjs-extensions.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9_rxjs_Rx__ = __webpack_require__("../../../../rxjs/Rx.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9_rxjs_Rx___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_9_rxjs_Rx__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__default_default_component__ = __webpack_require__("../../../../../src/app/default/default.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__security_security_module__ = __webpack_require__("../../../../../src/app/security/security.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__email_email_module__ = __webpack_require__("../../../../../src/app/email/email.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__dashboard_dashboard_module__ = __webpack_require__("../../../../../src/app/dashboard/dashboard.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14__core_global_service__ = __webpack_require__("../../../../../src/app/core/global.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15__core_authorization_service__ = __webpack_require__("../../../../../src/app/core/authorization.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_16_ng2_google_charts__ = __webpack_require__("../../../../ng2-google-charts/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_16_ng2_google_charts___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_16_ng2_google_charts__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_17__analytics_tab_component__ = __webpack_require__("../../../../../src/app/analytics/tab.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_18__analytics_tabs_component__ = __webpack_require__("../../../../../src/app/analytics/tabs.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_19__analytics_analytics_root_component__ = __webpack_require__("../../../../../src/app/analytics/analytics.root.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_20_angular2_http_file_upload__ = __webpack_require__("../../../../angular2-http-file-upload/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_20_angular2_http_file_upload___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_20_angular2_http_file_upload__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};








//import { FileUploadComponent } from './email/contact/fileupload.component';













//import { DataTableModule,SharedModule } from 'primeng/primeng';
var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
AppModule = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1__angular_core__["NgModule"])({
        declarations: [
            __WEBPACK_IMPORTED_MODULE_5__app_component__["a" /* AppComponent */],
            __WEBPACK_IMPORTED_MODULE_7__home_home_component__["a" /* HomeComponent */],
            __WEBPACK_IMPORTED_MODULE_10__default_default_component__["a" /* DefaultComponent */],
            // DashboardComponent,
            //FileUploadComponent,
            __WEBPACK_IMPORTED_MODULE_17__analytics_tab_component__["a" /* Tab */],
            __WEBPACK_IMPORTED_MODULE_18__analytics_tabs_component__["a" /* Tabs */],
            __WEBPACK_IMPORTED_MODULE_19__analytics_analytics_root_component__["a" /* AnalyticsComponent */]
        ],
        imports: [
            __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["BrowserModule"],
            __WEBPACK_IMPORTED_MODULE_2__angular_forms__["FormsModule"],
            __WEBPACK_IMPORTED_MODULE_3__angular_http__["a" /* HttpModule */], __WEBPACK_IMPORTED_MODULE_3__angular_http__["b" /* JsonpModule */],
            __WEBPACK_IMPORTED_MODULE_4__angular_material__["a" /* MaterialModule */].forRoot(),
            __WEBPACK_IMPORTED_MODULE_6__app_routing_module__["a" /* EmailAppRoutingModule */],
            __WEBPACK_IMPORTED_MODULE_11__security_security_module__["a" /* SecurityModule */],
            __WEBPACK_IMPORTED_MODULE_12__email_email_module__["a" /* EmailModule */],
            __WEBPACK_IMPORTED_MODULE_13__dashboard_dashboard_module__["a" /* DashboardModule */],
            //DataTableModule,SharedModule,
            __WEBPACK_IMPORTED_MODULE_16_ng2_google_charts__["Ng2GoogleChartsModule"]
        ],
        providers: [__WEBPACK_IMPORTED_MODULE_14__core_global_service__["a" /* GlobalService */], __WEBPACK_IMPORTED_MODULE_15__core_authorization_service__["a" /* AuthorizationService */], __WEBPACK_IMPORTED_MODULE_20_angular2_http_file_upload__["Uploader"]],
        bootstrap: [__WEBPACK_IMPORTED_MODULE_5__app_component__["a" /* AppComponent */]]
    })
], AppModule);

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/app.module.js.map

/***/ }),

/***/ "../../../../../src/app/core/authorization.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__global_service__ = __webpack_require__("../../../../../src/app/core/global.service.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AuthorizationService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var AuthorizationService = (function () {
    function AuthorizationService(globalService) {
        this.globalService = globalService;
    }
    AuthorizationService.prototype.isUserHasRole = function (role) {
        if (this.globalService.loggedInUser) {
            //console.log("Check for role : "+role+" | has access : "+this.globalService.loggedInUser.uiRoles.indexOf(role));
            if (this.globalService.loggedInUser.userType === 'ACC_TYPE_SUPER_ADMIN') {
                //console.log("Super admin found.. roles available by default");
                return true;
            }
            else if (this.globalService.loggedInUser.uiRoles.indexOf(role) >= 0) {
                return true;
            }
        }
        return false;
    };
    AuthorizationService.prototype.isUserAdmin = function () {
        if (this.globalService.loggedInUser.userType === 'ACC_TYPE_SUPER_ADMIN' || this.globalService.loggedInUser.userType === 'ACC_TYPE_ADMIN') {
            //console.log("Super admin found.. roles available by default");
            return true;
        }
        else {
            return false;
        }
    };
    AuthorizationService.prototype.isUserSuperAdmin = function () {
        if (this.globalService.loggedInUser.userType === 'ACC_TYPE_SUPER_ADMIN') {
            //console.log("Super admin found.. roles available by default");
            return true;
        }
        else {
            return false;
        }
    };
    return AuthorizationService;
}());
AuthorizationService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__global_service__["a" /* GlobalService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__global_service__["a" /* GlobalService */]) === "function" && _a || Object])
], AuthorizationService);

var _a;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/authorization.service.js.map

/***/ }),

/***/ "../../../../../src/app/core/global.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return GlobalService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var GlobalService = (function () {
    function GlobalService() {
    }
    return GlobalService;
}());
GlobalService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [])
], GlobalService);

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/global.service.js.map

/***/ }),

/***/ "../../../../../src/app/core/model/base.entity.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return BaseEntity; });
var BaseEntity = (function () {
    function BaseEntity() {
    }
    return BaseEntity;
}());

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/base.entity.js.map

/***/ }),

/***/ "../../../../../src/app/dashboard/dashboard.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../node_modules/css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "#summary-left,#summary-right\r\n{\r\n\tpadding:5px;\r\n}\r\n\r\n#summary-left h4,#summary-right h4 {\r\n\tcolor:#2556B9;\r\n}\r\n\r\n#summary-left h6,#summary-right h6 {\r\n\tcolor:#2198CB;\r\n}\r\n\r\n#summary-left {\r\n\tdisplay:inline-block;\r\n\twidth:50%;\r\n}\r\n#summary-right {\r\n\tdisplay:inline-block;\r\n\tposition:relative;\r\n\twidth:44%;\r\n\tpadding-left:10%;\r\n}\r\n\r\n#summaryContainer {\r\n\toverflow:hidden;\r\n}\r\n\r\ntable tr\r\n{\r\n    padding:7px;\r\n}\r\n\r\n#chart {\r\n\tdisplay: inline-block;\r\n    width: 52%;\r\n    padding-top: 1.5%;\r\n}\r\n#tabularData {\r\n\tdisplay: inline-block;\r\n    position: absolute;\r\n    width: 46%;\r\n    margin-top: 20px;\r\n    padding-left: 7.5%;\r\n}\r\n\r\n#tabularData h4 {\r\n\tcolor:#2556B9;\r\n}\r\n\r\n#tabularData table {\r\n\twidth:98%;\r\n}\r\n\r\n#tabularData table thead tr {\r\n\tbackground-color:#604688;\r\n\tcolor:white;\r\n}\r\n\r\n#tabularData table tr td {\r\n\tpadding:6px;\r\n}\r\n#tabularData table tr.totalreach {\r\n\twidth:45%;\r\n\tbackground-color:#F7F7F9;\r\n}\r\n\r\n#tabularData table tr.odd {\r\n\tbackground-color:#F7F7F9;\r\n}\r\n\r\n#tabularData table tr.even {\r\n\tbackground-color:#FEFDFE;\r\n}\r\n\r\n#tabularData table tr.clicks {\r\n\twidth:45%;\r\n\tbackground-color:#FEFDFE;\r\n}\r\n\r\n#tabularData table tr.unsubscribe {\r\n\twidth:45%;\r\n\tbackground-color:#F7F7F9;\r\n}\r\n\r\nmd-card-content {\r\n\theight:100%;\r\n\toverflow:hidden;\r\n}\r\nmd-card {\r\nbox-shadow: -7px 28px 66px -25px rgba(0,0,0,0.75);\r\nmargin-top:2vh;\r\nbackground:white;\r\nborder-radius: 12px 12px 12px 12px;\r\n-moz-border-radius: 12px 12px 12px 12px;\r\n-webkit-border-radius: 12px 12px 12px 12px;\r\nborder: 0px solid #000000;\r\n}", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/dashboard/dashboard.component.html":
/***/ (function(module, exports) {

module.exports = "<p-growl [value]=\"msgs\"></p-growl>\r\n<script src=\"https://www.gstatic.com/charts/loader.js\"></script>\r\n<script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script>\r\n<script type=\"text/javascript\">        \r\n  google.load('visualization', '1.1', {\r\n    'packages': ['corechart','bar']\r\n  });\r\n  \r\n</script>\r\n<div class=\"dashboardMainContainer\" style=\"min-height:110vh;\">\r\n<md-card class=\"app-input-section\">\r\n\t<div id=\"refresh-button\">\r\n\t\t\t<!--button type=\"submit\" class=\"btn btn-success btn-s\" (click)=\"refreshStats()\">Refresh Statistics</button-->\r\n\t\t\t<button type=\"button\" class=\"btn btn-success btn-s\" (click)=\"refreshStats()\" ripple-radius style=\"float:right;margin-right:20px;\">Refresh Statistics</button>\r\n\t</div>\r\n\t\t\t<md-card-title>\r\n\t\t\t\t\t<span><img src=\"../../app/resources/images/jobstatsicon.png\" style=\"width:65px;height:60px;margin-right:15px;opacity:100;\"/></span><span>Job Statistics (Queued)</span>\r\n\t\t\t</md-card-title>\r\n\t\t\t<md-card-content>\r\n\t\t\t\t<p-dataTable [value]=\"jobstatusdataQueued\" [rows]=\"10\" [paginator]=\"true\"\r\n        [pageLinks]=\"3\" [rowsPerPageOptions]=\"[10,20]\" selectionMode=\"single\" [(selection)]=\"selectedJob\" [responsive]=\"true\" [contextMenu]=\"cm\">\r\n        <header>Job Status Statistics (Queued)</header>\r\n\t\t\t\t<header>Right click on a row to request cancellation</header>\r\n        <p-column  field=\"jobId\" header=\"Job ID\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n        <p-column  field=\"batchId\" header=\"Batch Id\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n        <p-column  field=\"requestId\" header=\"Request ID\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n        <p-column  field=\"campaignName\" header=\"Campaign Name\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n\t\t\t\t<p-column  field=\"emailCount\" header=\"Total Contacts\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n\t\t\t\t<p-column  field=\"sender\" header=\"Sender\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n\t\t\t\t<p-column  field=\"status\" header=\"Status\" [sortable]=\"true\" [filter]=\"false\"></p-column>\r\n        <footer>\r\n        </footer>\r\n      </p-dataTable>\r\n\t\t\t<p-contextMenu #cm [model]=\"datatable_menu_items\"></p-contextMenu>\r\n\t\t\t</md-card-content>\r\n\t\t\t<br><br>\r\n\t\t\t<md-card-title>\r\n\t\t\t\t\t<span><img src=\"../../app/resources/images/jobcompletedicon.png\" style=\"width:60px;height:60px;margin-right:15px;opacity:100;\"/></span><span> Job Statistics (Completed)</span>\r\n\t\t\t</md-card-title>\r\n\t\t\t<md-card-content>\r\n\t\t\t\t<p-dataTable [value]=\"jobstatusdataCompleted\" [rows]=\"10\" [paginator]=\"true\"\r\n        [pageLinks]=\"3\" [rowsPerPageOptions]=\"[10,20]\" selectionMode=\"single\" [responsive]=\"true\">\r\n        <header>Job Status Statistics (Completed)</header>\r\n        <p-column field=\"jobId\" header=\"Job ID\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n        <p-column field=\"batchId\" header=\"Batch Id\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n        <p-column field=\"requestId\" header=\"Request ID\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n        <p-column field=\"campaignName\" header=\"Campaign Name\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n\t\t\t\t<p-column field=\"emailCount\" header=\"Total Contacts\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n\t\t\t\t<p-column field=\"sender\" header=\"Sender\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n\t\t\t\t<p-column field=\"status\" header=\"Status\" [sortable]=\"true\" [filter]=\"false\"></p-column>\r\n\t\t\t\t<p-column field=\"comments\" header=\"Remarks\" [sortable]=\"true\" [filter]=\"false\"></p-column>\r\n        <footer>\r\n        </footer>\r\n      </p-dataTable>\r\n\t\t\t</md-card-content>\r\n\t\t\t<br><br>\r\n\t\t\t\t\t\t<md-card-title>\r\n\t\t\t\t\t<span><img src=\"../../app/resources/images/jobfailedicon.png\" style=\"width:60px;height:60px;margin-right:15px;opacity:100;\"/></span><span>Job Statistics (Failed)</span>\r\n\t\t\t</md-card-title>\r\n\t\t\t<md-card-content>\r\n\t\t\t\t<p-dataTable [value]=\"jobstatusdataFailed\" [rows]=\"10\" [paginator]=\"true\"\r\n        [pageLinks]=\"3\" [rowsPerPageOptions]=\"[10,20]\" selectionMode=\"single\" [responsive]=\"true\">\r\n        <header>Job Status Statistics (Failed)</header>\r\n        <p-column field=\"jobId\" header=\"Job ID\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n        <p-column field=\"batchId\" header=\"Batch Id\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n        <p-column field=\"requestId\" header=\"Request ID\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n        <p-column field=\"campaignName\" header=\"Campaign Name\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n\t\t\t\t<p-column field=\"emailCount\" header=\"Total Contacts\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n\t\t\t\t<p-column field=\"sender\" header=\"Sender\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n\t\t\t\t<!--p-column field=\"status\" header=\"Status\" [sortable]=\"true\" [filter]=\"false\"></p-column-->\r\n\t\t\t\t<p-column field=\"comments\" header=\"Failure Reason\" [sortable]=\"true\" [filter]=\"false\"></p-column>\r\n        <footer>\r\n        </footer>\r\n      </p-dataTable>\r\n\t\t\t</md-card-content>\r\n\t\t\t<br><br>\r\n\t\t\t<div *ngIf=\"admin\">\r\n\t\t\t<md-card-title>\r\n\t\t\t\t\t<span><img src=\"../../app/resources/images/blockedcontacticon.png\" style=\"width:60px;height:60px;margin-right:15px;opacity:100;\"/></span><span>Blocked / Bounced Contacts</span>\r\n\t\t\t</md-card-title>\r\n\t\t\t<md-card-content>\r\n\t\t\t\t<p-dataTable [value]=\"blockedContacts\" [rows]=\"10\" [paginator]=\"true\"\r\n        \t[pageLinks]=\"3\" [rowsPerPageOptions]=\"[10,20]\" selectionMode=\"single\" [responsive]=\"true\">\r\n        \t<header>Contacts Blocked due to Bounces / Failures</header>\r\n        \t<p-column field=\"firstName\" header=\"First Name\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n        \t<p-column field=\"lastName\" header=\"Last Name\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n        \t<p-column field=\"email\" header=\"Email\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n        \t<p-column field=\"reason\" header=\"Reason\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n\t\t\t\t\t<p-column field=\"responseCode\" header=\"Response Code\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n        \t<footer>\r\n        \t</footer>\r\n      \t</p-dataTable>\r\n\t\t\t</md-card-content>\r\n\t\t\t</div>\r\n\t\t\t<br><br>\r\n      <md-card-title>\r\n\t\t\t\t<span><img src=\"../../app/resources/images/summary.png\" style=\"width:60px;height:60px;margin-right:15px;opacity:100;\"/></span><span>Recent Campaign Summary</span>\r\n\t  </md-card-title>\r\n      <md-card-content>\r\n              <p> See your most recent campaign summary here </p>\r\n\t\t\t  <div id=\"summaryContainer\">\t\t\t  \r\n\t\t\t\t  <div id=\"summary-left\">\r\n\t\t\t\t\t<h4 #emailSubject></h4>\r\n\t\t\t\t\t<h6 #sentOnDate></h6>\r\n\t\t\t\t  </div>\r\n\t\t\t\t  <div id=\"summary-right\">\r\n\t\t\t\t\t<h4> Statistical Summary </h4>\r\n\t\t\t\t\t<p #description>\r\n\t\t\t\t\t\tThis is a section that gives a summarized text for the most recent campaign held from your account. It provides insight on whom the campaign was targeted to and to which groups. \r\n\t\t\t\t\t\tIt also provides insights on the statistical insights for the campaign.\r\n\t\t\t\t\t</p>\r\n\t\t\t\t  </div>\r\n\t\t\t  </div>\r\n\t\t\t  <div id=\"chartContainer\">\r\n\t\t\t    <div id=\"chart\">\r\n\t\t\t\t\t\t<google-chart [data]='barChartOptions'></google-chart>\r\n\t\t\t\t\t</div>\r\n\t\t\t\t\t<div id=\"tabularData\">\r\n\t\t\t\t\t\t<table>\r\n\t\t\t\t\t\t\t<thead>\r\n\t\t\t\t\t\t\t\t<tr class=\"odd\">\r\n\t\t\t\t\t\t\t\t\t<td>Statistical Analysis</td>\r\n\t\t\t\t\t\t\t\t\t<td>Hit Percentage</td>\r\n\t\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t\t</thead>\r\n\t\t\t\t\t\t\t<tr class=\"totalreach\">\r\n\t\t\t\t\t\t\t\t<td>Total Reach</td>\r\n\t\t\t\t\t\t\t\t<td #reachPercentage></td>\r\n\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t\t<tr class=\"clicks\">\r\n\t\t\t\t\t\t\t\t<td>Total clicks</td>\r\n\t\t\t\t\t\t\t\t<td #clickPercentage></td>\r\n\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t\t<tr class=\"unsubscribe\">\r\n\t\t\t\t\t\t\t\t<td>Usubscribes</td>\r\n\t\t\t\t\t\t\t\t<td #unsubscribePercentage></td>\r\n\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t</table>\t\t\t\t\t\r\n\t\t\t\t\t</div>\r\n\t\t\t  </div>\r\n      </md-card-content>\r\n\t\t\t<md-card-title>\r\n\t\t\t\t<span><img src=\"../../app/resources/images/unsubscribedicon.png\" style=\"width:60px;height:60px;margin-right:15px;opacity:100;\"/></span><span>Recent Unsubscriptions</span>\r\n\t  \t</md-card-title>\r\n\t\t\t<md-card-content>\r\n\t\t\t  <p> Check how many people have opted out of this portal </p>\r\n\t\t\t  <div id=\"summaryContainer\">\r\n\t\t\t\t\t<div id=\"summary-left\">\r\n\t\t\t\t\t\t<h4>Recent Unsubscription Statistics</h4>\r\n\t\t\t\t\t\t<h6>A graphical representation of number of unsubscriptions per day</h6>\r\n\t\t\t\t  </div>\t  \r\n\t\t\t\t  <div id=\"summary-right\">\r\n\t\t\t\t\t<h4> Unsubscription records </h4>\r\n\t\t\t\t\t<p>\r\n\t\t\t\t\t\tThis is a section that gives a summary of recent unsubscribes for the last 60 days\r\n\t\t\t\t\t</p>\r\n\t\t\t\t  </div>\r\n\t\t\t  </div>\r\n\t\t\t  <div id=\"chartContainer\">\r\n\t\t\t  \t<div id=\"chart\">\r\n\t\t\t\t\t\t<google-chart [data]='unsubscribeLineChartOptions'></google-chart>\r\n\t\t\t\t\t</div>\r\n\t\t\t\t\t<div id=\"tabularData\">\r\n\t\t\t\t    <table>\r\n                <thead>\r\n                    <tr>\r\n                        <td>Serial No</td>\r\n                        <td>Contact Name</td>\r\n                        <td>Email</td>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<td>Date</td>\r\n                    </tr>\r\n                </thead>\r\n                <tbody>\r\n                    <tr *ngIf=\"noDataFound\">\r\n                        <td colspan=\"8\">No unsubscriptions found</td>            \r\n                    </tr>\r\n                        <tr *ngFor=\"let data of unsubscribes\" [ngClass]=\"getClass(data.serialNo)\">\r\n                            <td>{{data.serialNo}}</td>\r\n                            <td>{{data.firstName}} {{data.lastName}}</td>\r\n                            <td>{{data.email}}</td>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td>{{data.unsubscribedOn}}</td>\r\n                        </tr>\r\n                </tbody>\r\n            </table>\t\t\t\t\t\r\n\t\t\t\t\t</div>\r\n\t\t\t  </div>\r\n      </md-card-content>\r\n</md-card>\r\n</div>\r\n"

/***/ }),

/***/ "../../../../../src/app/dashboard/dashboard.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__analytics_analytics_service__ = __webpack_require__("../../../../../src/app/analytics/analytics.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_router__ = __webpack_require__("../../../router/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__core_global_service__ = __webpack_require__("../../../../../src/app/core/global.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__core_authorization_service__ = __webpack_require__("../../../../../src/app/core/authorization.service.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DashboardComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var DashboardComponent = (function () {
    function DashboardComponent(AnalyticsService, router, globalService, authService) {
        this.AnalyticsService = AnalyticsService;
        this.router = router;
        this.globalService = globalService;
        this.authService = authService;
        this.admin = false;
        this.msgs = [];
        this.noDataFound = false;
        this.jobstatusdataQueued = [];
        this.jobstatusdataCompleted = [];
        this.jobstatusdataFailed = [];
        this.unsubscribeLineChartOptions = {
            chartType: 'LineChart',
            dataTable: [
                [{ label: 'Date', type: 'string' }, { label: 'Nmber of Unsubscriptions', type: 'number' }]
            ],
            options: {
                title: 'Recent Unsubscribes',
                animation: {
                    duration: 1000,
                    easing: 'out',
                    startup: true
                },
                vAxis: {
                    title: 'Count'
                },
                hAxis: {
                    title: 'Date'
                },
                width: '95%',
                height: '350'
            }
        };
        this.barChartOptions = {
            chartType: 'BarChart',
            dataTable: [
                ['Metrics Type', 'Count'],
                ['Total Reach', 0],
                ['Total Clicks', 0],
                ['Unsubscribed', 0]
            ],
            options: {
                width: '95%',
                chartArea: { width: '65%', right: '15%', top: '1%' },
                bar: {
                    groupWidth: '50%'
                },
                animation: {
                    duration: 1000,
                    easing: 'out',
                    startup: true
                },
                hAxis: {
                    title: 'Count',
                    minValue: 0,
                    textStyle: {
                        bold: true,
                        fontSize: 11,
                        color: '#4d4d4d'
                    },
                    titleTextStyle: {
                        bold: true,
                        fontSize: 12,
                        color: '#4d4d4d'
                    }
                },
                vAxis: {
                    title: 'Categories',
                    textStyle: {
                        fontSize: 11,
                        bold: true,
                    },
                    titleTextStyle: {
                        fontSize: 14,
                        bold: true,
                        color: '#848484'
                    }
                }
            }
        };
        var user = this.globalService.loggedInUser.loggedInUserName;
        if (this.globalService.loggedInUser.userType === 'ACC_TYPE_SUPER_ADMIN' || this.globalService.loggedInUser.userType === 'ACC_TYPE_ADMIN') {
            this.admin = true;
            this.isAdmin = "true";
            this.getBlockedContacts();
        }
        else {
            this.isAdmin = "false";
        }
        this.recentChartSummary(user);
        this.recentUnsubscribedCount(60);
        this.recentUnsubscribes(60);
        this.getJobStatusDump(user);
        //console.log("User Type : "+this.globalService.loggedInUser.userType);
    }
    DashboardComponent.prototype.getBlockedContacts = function () {
        var _this = this;
        this.AnalyticsService.getBlockedContacts()
            .subscribe(function (data) {
            _this.blockedContacts = data;
        }, function (error) {
        });
    };
    DashboardComponent.prototype.getJobStatusDump = function (userName) {
        var _this = this;
        this.AnalyticsService.getJobStatusData(userName)
            .subscribe(function (data) {
            //this.jobstatusdata = data;
            for (var _i = 0, data_1 = data; _i < data_1.length; _i++) {
                var entry = data_1[_i];
                if (entry.status === 'COMPLETED') {
                    _this.jobstatusdataCompleted.push(entry);
                }
                else if (entry.status === 'QUEUED') {
                    _this.jobstatusdataQueued.push(entry);
                }
                else if (entry.status === 'FAILED') {
                    _this.jobstatusdataFailed.push(entry);
                }
            }
        }, function (error) {
        });
    };
    DashboardComponent.prototype.cancelJob = function (data) {
        var _this = this;
        this.AnalyticsService.cancelJob(data.batchId, data.requestId)
            .subscribe(function (data) {
            _this.msgs.push({ severity: "info", summary: "Job Cancelled", detail: "The cancellation request was procesed successfully" });
        }, function (error) {
            _this.msgs.push({ severity: "error", summary: "Failed to process cancellation request", detail: error });
        });
    };
    DashboardComponent.prototype.refreshStats = function () {
        this.clear();
        var user = this.globalService.loggedInUser.loggedInUserName;
        this.getJobStatusDump(user);
    };
    DashboardComponent.prototype.clear = function () {
        this.jobstatusdataQueued.splice(0);
        this.jobstatusdataCompleted.splice(0);
        this.jobstatusdataFailed.splice(0);
    };
    DashboardComponent.prototype.getClass = function (id) {
        if (id % 2 === 0) {
            // console.log("id "+id+" Return class as even");
            return "even";
        }
        else {
            // console.log("id "+id+"Return class as odd");
            return "odd";
        }
    };
    DashboardComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.datatable_menu_items = [
            { label: 'Cancel', icon: 'fa-close', command: function (event) { return _this.cancelJob(_this.selectedJob); } }
        ];
    };
    DashboardComponent.prototype.loadData = function (sentOn, subject, clickPercentage, unsubscribePercentage) {
        this.sentOnDate.nativeElement.innerHTML = sentOn;
        this.emailSubject.nativeElement.innerHTML = subject;
        this.clickPercentage.nativeElement.innerHTML = clickPercentage + '%';
        this.unsubscribePercentage.nativeElement.innerHTML = unsubscribePercentage + '%';
        if (this.summary.reach === 0) {
            this.reachPercentage.nativeElement.innerHTML = '0%';
        }
        else {
            this.reachPercentage.nativeElement.innerHTML = '100%';
        }
    };
    DashboardComponent.prototype.recentChartSummary = function (username) {
        var _this = this;
        this.AnalyticsService.recentChartSummary(username)
            .subscribe(function (data) {
            _this.summary = data;
            console.log("data: ", data);
            console.log("response : " + _this.summary.unsubscribes + " | " + _this.summary.reach + " | " + _this.summary.clicks);
            if (_this.summary.unsubscribes === 0) {
                _this.summary.unsubscribePercentage = 0;
            }
            else {
                _this.summary.unsubscribePercentage = (_this.summary.unsubscribes / _this.summary.reach) * 100;
            }
            if (_this.summary.clicks === 0) {
                _this.summary.clickPercentage = 0;
            }
            else {
                _this.summary.clickPercentage = (_this.summary.clicks / _this.summary.reach) * 100;
            }
            _this.myClick();
            _this.loadData("Sent on " + _this.summary.sentOn, _this.summary.subject, _this.summary.clickPercentage, _this.summary.unsubscribePercentage);
        }, function (error) {
        });
    };
    ;
    DashboardComponent.prototype.recentUnsubscribes = function (age) {
        var _this = this;
        this.AnalyticsService.recentUnsubscribes(age, this.isAdmin)
            .subscribe(function (data) {
            _this.unsubscribes = data;
            if (_this.unsubscribes.length === 0) {
                _this.noDataFound = true;
            }
            console.log("unsubscribes: ", data);
        }, function (error) {
            _this.noDataFound = true;
        });
    };
    ;
    DashboardComponent.prototype.recentUnsubscribedCount = function (age) {
        var _this = this;
        this.AnalyticsService.recentUnsubscribedCount(age, this.isAdmin)
            .subscribe(function (data) {
            _this.unsubscribedCount = data;
            console.log("unsubscribedCount: ", data);
            _this.myRecentUnsubscribedCountClick();
        }, function (error) {
        });
    };
    ;
    DashboardComponent.prototype.ready = function (event) {
        console.log("Chart Ready", event.message);
    };
    DashboardComponent.prototype.myRecentUnsubscribedCountClick = function () {
        this.unsubscribeLineChartOptions = Object.create(this.unsubscribeLineChartOptions);
        var i = 0;
        for (var _i = 0, _a = this.unsubscribedCount; _i < _a.length; _i++) {
            var entry = _a[_i];
            this.unsubscribeLineChartOptions.dataTable.push([entry.unsubscribedOn, entry.count]);
        }
    };
    DashboardComponent.prototype.myClick = function () {
        // forces a reference update (otherwise angular doesn't detect the change)
        this.barChartOptions = Object.create(this.barChartOptions);
        this.barChartOptions.dataTable[1][1] = this.summary.reach;
        this.barChartOptions.dataTable[2][1] = this.summary.clicks;
        this.barChartOptions.dataTable[3][1] = this.summary.unsubscribes;
    };
    return DashboardComponent;
}());
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["ViewChild"])('sentOnDate'),
    __metadata("design:type", typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_0__angular_core__["ElementRef"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_0__angular_core__["ElementRef"]) === "function" && _a || Object)
], DashboardComponent.prototype, "sentOnDate", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["ViewChild"])('emailSubject'),
    __metadata("design:type", typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_0__angular_core__["ElementRef"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_0__angular_core__["ElementRef"]) === "function" && _b || Object)
], DashboardComponent.prototype, "emailSubject", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["ViewChild"])('clickPercentage'),
    __metadata("design:type", typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_0__angular_core__["ElementRef"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_0__angular_core__["ElementRef"]) === "function" && _c || Object)
], DashboardComponent.prototype, "clickPercentage", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["ViewChild"])('unsubscribePercentage'),
    __metadata("design:type", typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_0__angular_core__["ElementRef"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_0__angular_core__["ElementRef"]) === "function" && _d || Object)
], DashboardComponent.prototype, "unsubscribePercentage", void 0);
__decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["ViewChild"])('reachPercentage'),
    __metadata("design:type", typeof (_e = typeof __WEBPACK_IMPORTED_MODULE_0__angular_core__["ElementRef"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_0__angular_core__["ElementRef"]) === "function" && _e || Object)
], DashboardComponent.prototype, "reachPercentage", void 0);
DashboardComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-dashboard',
        template: __webpack_require__("../../../../../src/app/dashboard/dashboard.component.html"),
        styles: [__webpack_require__("../../../../../src/app/dashboard/dashboard.component.css"), __webpack_require__("../../../../../src/app/email/contact/fileupload.component.css")],
        providers: [__WEBPACK_IMPORTED_MODULE_1__analytics_analytics_service__["a" /* AnalyticsService */]]
    }),
    __metadata("design:paramtypes", [typeof (_f = typeof __WEBPACK_IMPORTED_MODULE_1__analytics_analytics_service__["a" /* AnalyticsService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__analytics_analytics_service__["a" /* AnalyticsService */]) === "function" && _f || Object, typeof (_g = typeof __WEBPACK_IMPORTED_MODULE_2__angular_router__["Router"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_router__["Router"]) === "function" && _g || Object, typeof (_h = typeof __WEBPACK_IMPORTED_MODULE_3__core_global_service__["a" /* GlobalService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__core_global_service__["a" /* GlobalService */]) === "function" && _h || Object, typeof (_j = typeof __WEBPACK_IMPORTED_MODULE_4__core_authorization_service__["a" /* AuthorizationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__core_authorization_service__["a" /* AuthorizationService */]) === "function" && _j || Object])
], DashboardComponent);

var _a, _b, _c, _d, _e, _f, _g, _h, _j;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/dashboard.component.js.map

/***/ }),

/***/ "../../../../../src/app/dashboard/dashboard.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common__ = __webpack_require__("../../../common/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_material__ = __webpack_require__("../../../material/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_primeng_primeng__ = __webpack_require__("../../../../primeng/primeng.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_primeng_primeng___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_primeng_primeng__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__analytics_analytics_service__ = __webpack_require__("../../../../../src/app/analytics/analytics.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__dashboard_component__ = __webpack_require__("../../../../../src/app/dashboard/dashboard.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_ng2_google_charts__ = __webpack_require__("../../../../ng2-google-charts/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_ng2_google_charts___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_6_ng2_google_charts__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DashboardModule; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};









var DashboardModule = (function () {
    function DashboardModule() {
    }
    return DashboardModule;
}());
DashboardModule = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["NgModule"])({
        declarations: [__WEBPACK_IMPORTED_MODULE_5__dashboard_component__["a" /* DashboardComponent */]],
        imports: [
            __WEBPACK_IMPORTED_MODULE_3_primeng_primeng__["DataTableModule"],
            __WEBPACK_IMPORTED_MODULE_3_primeng_primeng__["SharedModule"],
            __WEBPACK_IMPORTED_MODULE_1__angular_common__["CommonModule"],
            __WEBPACK_IMPORTED_MODULE_2__angular_material__["a" /* MaterialModule */].forRoot(),
            __WEBPACK_IMPORTED_MODULE_3_primeng_primeng__["GrowlModule"],
            __WEBPACK_IMPORTED_MODULE_3_primeng_primeng__["DialogModule"],
            __WEBPACK_IMPORTED_MODULE_6_ng2_google_charts__["Ng2GoogleChartsModule"],
            __WEBPACK_IMPORTED_MODULE_3_primeng_primeng__["ButtonModule"],
            __WEBPACK_IMPORTED_MODULE_3_primeng_primeng__["ContextMenuModule"]
        ],
        providers: [__WEBPACK_IMPORTED_MODULE_4__analytics_analytics_service__["a" /* AnalyticsService */]]
    })
], DashboardModule);

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/dashboard.module.js.map

/***/ }),

/***/ "../../../../../src/app/dashboard/index.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__dashboard_component__ = __webpack_require__("../../../../../src/app/dashboard/dashboard.component.ts");
/* harmony namespace reexport (by used) */ __webpack_require__.d(__webpack_exports__, "a", function() { return __WEBPACK_IMPORTED_MODULE_0__dashboard_component__["a"]; });

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/index.js.map

/***/ }),

/***/ "../../../../../src/app/default/default.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../node_modules/css-loader/lib/css-base.js")(false);
// imports
exports.push([module.i, "@import url(https://fonts.googleapis.com/css?family=Montserrat);", ""]);

// module
exports.push([module.i, "section {\r\n  height: 100vh;\r\n  position: relative;\r\n  width: 100%;\r\n}\r\nsection:nth-child(1) {\r\n  background-color: #FFF;\r\n}\r\nsection:nth-child(2) {\r\n  background-color: #FFF;\r\n}\r\nsection:nth-child(3) {\r\n  background-color: #fff;\r\n}\r\nsection h1 {\r\n  color: #000;\r\n  position: absolute;\r\n  font-size: 60px;  \r\n  -webkit-transform: translate(-50%, -50%);  \r\n          transform: translate(-50%, -50%);\r\n  top: 10%;\r\n  left: 50%;\r\nposition: absolute;\r\n  font-family: 'Montserrat', sans-serif;\r\n}\r\n\r\n.main-p{\r\n      top: 18%;\r\n    left: 30%;\r\n    position: absolute;\r\n}\r\n\r\n#section1{\r\n  background: url(/../../app/resources/images/content/cover_main.jpg);\r\n  background-repeat: no-repeat;\r\n  background-size: cover;\r\n  height: 100%;\r\n\r\n}\r\n\r\n#main-bt{\r\n    top: 25%;\r\n    left: 46%;\r\n    position: absolute;\r\n    width: 130px;\r\n    font-family: 'Montserrat', sans-serif;\r\n    padding: 10px 10px 10px 10px;\r\n    font-size: 15px;\r\n    background: #686868;\r\n}\r\n\r\n@media screen and (min-width: 480px) {\r\n    section h1 {\r\n        font-size: 21px;\r\n    }\r\n      .main-p{\r\n        left: 14%;\r\n        padding-right: 20px;\r\n      }\r\n}\r\n\r\n@media screen and (min-width: 760px) {\r\n    section h1 {\r\n        font-size: 39px;\r\n    }\r\n      .main-p{\r\n        left: 12%;\r\n      }\r\n\r\n      #main-bt{\r\n        left: 39%;\r\n      }\r\n}\r\n\r\n@media screen and (min-width: 970px) {\r\n    section h1 {\r\n        font-size: 50px;\r\n    }\r\n      .main-p{\r\n        left: 17%;\r\n      }\r\n\r\n}\r\n\r\n@media screen and (min-width: 1170px) {\r\n    section h1 {\r\n        font-size: 60px;\r\n    }\r\n      .main-p{\r\n        left: 29%;\r\n      }\r\n\r\n      #main-bt{\r\n        left: 45%;\r\n      }\r\n\r\n}\r\n\r\n@media screen and (min-width: 1920px) {\r\n    section h1 {\r\n        font-size: 60px;\r\n    }\r\n      .main-p{\r\n        left: 33%;\r\n      }\r\n\r\n      #main-bt{\r\n        left: 45%;\r\n      }\r\n\r\n}\r\n\r\n\r\n\r\n.services-section {\r\n  text-align: center;\r\n  background: #101010;\r\n}\r\n\r\n.service-holder {\r\n  border: 1px solid transparent;\r\n  margin-bottom: 30px;\r\n  margin-top: 20px;\r\n  padding-bottom: 30px;\r\n  padding-top: 30px;\r\n  transition: all 0.3s ease;\r\n}\r\n\r\n.service-holder > div {\r\n  height: 140px;\r\n  width: 140px;\r\n  background-color: transparent;\r\n  border-bottom-left-radius: 50%;\r\n  border-bottom-left-radius: 50%;\r\n  border-bottom-right-radius: 50%;\r\n  border-bottom-right-radius: 50%;\r\n  border-top-left-radius: 50%;\r\n  border-top-left-radius: 50%;\r\n  border-top-right-radius: 50%;\r\n  border-top-right-radius: 50%;\r\n  color: #4c505e;\r\n  font-size: 30px;\r\n  line-height: 140px;\r\n  margin: 0 auto;\r\n  margin-bottom: 25px;\r\n}\r\n\r\n.service-holder > div > i {\r\n  font-size: 70px;\r\n  line-height: 140px; \r\n  transition: all 0.27s cubic-bezier(0.000, 0.000, 0.580, 1.000);\r\n}\r\n\r\n.service-holder > div > i:hover {\r\n  color: #898989;\r\n  font-size: 70px;\r\n  line-height: 140px;\r\n}\r\n\r\n.service-holder h4 {\r\n  color: #898989;\r\n  font-family: 'Raleway', sans-serif;\r\n  font-weight: 600;\r\n  letter-spacing: 4px;\r\n  text-transform: uppercase;\r\n}\r\n\r\n.service-holder p {\r\n  font-family: 'Montserrat', sans-serif;\r\n  color: #000;\r\n  letter-spacing: 1px;\r\n}\r\n\r\n.service-holder a {\r\n  color: #898989; \r\n  transition: all 0.27s cubic-bezier(0.000, 0.000, 0.580, 1.000);\r\n}\r\n\r\n.service-holder a:hover {\r\n  color: #898989;\r\n  text-decoration: none;\r\n}\r\n\r\n.col-centered {\r\n  display: inline-block;\r\n  float: none;\r\n  margin-left: -4px;\r\n  vertical-align: bottom;\r\n}\r\n\r\n.footer{\r\n  display: table;\r\n    background-color: #1b3049;\r\n    height: 75px;\r\n    position: fixed;\r\n    bottom: 0;\r\n    left: 0;\r\n    width: 100%;\r\n}\r\n\r\n.footer p{\r\n      color: #fff;\r\n    padding-top: 25px;\r\n    float: right;\r\n    padding-right: 60px;\r\n}", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/default/default.component.html":
/***/ (function(module, exports) {

module.exports = "<section>\r\n  <div class=\"col-md-12\" id=\"section1\">\r\n    <h1>Grow Your Business</h1>\r\n    <p class=\"main-p\">Streamline your business, help grow it. We offer you with the power of advertising to build your dreams. </p>\r\n    <a class=\"btn btn-primary\" id=\"main-bt\" href=\"#\" role=\"button\">Get Started</a>\r\n  </div>\r\n</section>\r\n\r\n\r\n<section id=\"services\" class=\"services-section\" style=\"height:auto;\">\r\n  <div class=\"container pt-50 pb-50\" style=\"padding-top:30px;\">\r\n    <div class=\"row\" style=\"margin-top:30px;\">\r\n      <div class=\"col-xs-12 col-sm-12 col-md-12 col-lg-12 title\">\r\n    <h1 style=\"font-size:40px; color:#000; margin-bottom:60px;\">We get things done faster</h1>\r\n        <div class=\"horizontal-gold\"></div>\r\n      </div>\r\n      </div>\r\n        <div class=\"row\" style=\"margin-top:60px; margin-bottom:60px;\">\r\n      <div class=\"col-xs-12 col-sm-6 col-md-3 col-lg-3 service-holder text-center col-centered\">\r\n        <div><i class=\"fa fa-mobile\" aria-hidden=\"true\" style='line-height: 130px;'></i></div>\r\n        <h4>Mobile Friendly</h4>\r\n        <p>Work with campaign even on the go with responsive interfaces, and mobile responsive templates look professional</p>\r\n      </div>\r\n\r\n      <div class=\"col-xs-12 col-sm-6 col-md-3 col-lg-3 service-holder text-center col-centered\">\r\n        <div><i class=\"fa fa-bar-chart\" aria-hidden=\"true\"></i></div>\r\n        <h4>Easy Reporting</h4>\r\n        <p>Better reporting with real time data, get reporting for the campaign and imporve your business</p>\r\n      </div>\r\n\r\n      <div class=\"col-xs-12 col-sm-6 col-md-3 col-lg-3 service-holder text-center col-centered\">\r\n        <div><i class=\"fa fa-envelope\" aria-hidden=\"true\"></i></div>\r\n        <h4>Emails Reach</h4>\r\n        <p>Maximum success rate of email reach, never miss a customer and build your reputation develop your business.</p>\r\n      </div>\r\n\r\n      <div class=\"col-xs-12 col-sm-6 col-md-3 col-lg-3 service-holder text-center col-centered\">\r\n        <div><i class=\"fa fa-database\" aria-hidden=\"true\"></i></div>\r\n        <h4>Data</h4>\r\n        <p>Imporve the way you run the business with the data provided, help shaping your business better.</p>\r\n      </div>\r\n    </div>\r\n  </div>\r\n</section>\r\n\r\n\r\n<div class=\"col-md-12\">\r\n  <div class=\"footer\">\r\n    <p>&copy; 2017 Contact Swing</p>\r\n  </div>\r\n</div>\r\n\r\n\r\n<script type=\"text/javascript\">\r\n  (function () {\r\n\r\n    //t = current time\r\n    //b = start value\r\n    //c = change in value\r\n    //d = duration\r\n    Math.easeInOutQuad = function (t, b, c, d) {\r\n      t /= d / 2;\r\n      if (t < 1) return c / 2 * t * t + b;\r\n      t--;\r\n      return -c / 2 * (t * (t - 2) - 1) + b;\r\n    };\r\n\r\n    var app = {\r\n      cache: function () {\r\n        this.$body = document.documentEelement || document.body;\r\n      },\r\n      events: function () {\r\n        // If you comment the line below, the automatic scroll to next page will be deactivated;\r\n        window.onscroll = this.scrolling.bind(this);\r\n      },\r\n      init: function () {\r\n        this.scrollTop = 0;\r\n        this.isScrolling = false;\r\n        this.duration = 500;\r\n        this.vHeight = window.innerHeight;\r\n\r\n        this.cache();\r\n        this.events();\r\n      },\r\n      scrolling: function (e) {\r\n        var scrollTop;\r\n\r\n        if (this.isScrolling) return;\r\n\r\n        this.vHeight = window.innerHeight;\r\n        scrollTop = this.$body.scrollTop;\r\n\r\n        if (scrollTop > this.scrollTop)\r\n          this.scrollTo(this.$body, (this.scrollTop + this.vHeight), this.duration);\r\n        else\r\n          this.scrollTo(this.$body, (this.scrollTop - this.vHeight), this.duration);\r\n      },\r\n      scrollTo: function (element, to, duration) {\r\n        var start = element.scrollTop,\r\n          change = to - start,\r\n          currentTime = 0,\r\n          increment = 20,\r\n          self = this;\r\n\r\n        var animateScroll = function () {\r\n          currentTime += increment;\r\n          var val = Math.easeInOutQuad(currentTime, start, change, duration);\r\n          element.scrollTop = val;\r\n          if (currentTime <= duration) {\r\n            setTimeout(animateScroll, increment);\r\n          } else {\r\n            self.scrollTop = element.scrollTop;\r\n            setTimeout(function () { self.isScrolling = false }, increment);\r\n          }\r\n        };\r\n\r\n        this.isScrolling = true;\r\n        animateScroll();\r\n      }\r\n    };\r\n\r\n    app.init();\r\n\r\n  })();\r\n\r\n</script>"

/***/ }),

/***/ "../../../../../src/app/default/default.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__security_login_login_service__ = __webpack_require__("../../../../../src/app/security/login/login.service.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DefaultComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


//import {CarouselModule} from 'angular2-carousel-ztw/carousel.module';
var DefaultComponent = (function () {
    function DefaultComponent(loginService) {
        this.loginService = loginService;
        var userLoggedIn = sessionStorage.getItem('userLoggedIn');
        if (!userLoggedIn) {
            this.logout();
        }
    }
    DefaultComponent.prototype.logout = function () {
        sessionStorage.clear();
        this.loginService.logout().subscribe(function () {
            //window.location.reload();
        }, function (error) {
        });
    };
    DefaultComponent.prototype.ngOnInit = function () {
    };
    return DefaultComponent;
}());
DefaultComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-default',
        template: __webpack_require__("../../../../../src/app/default/default.component.html"),
        styles: [__webpack_require__("../../../../../src/app/default/default.component.css")]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__security_login_login_service__["a" /* LoginService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__security_login_login_service__["a" /* LoginService */]) === "function" && _a || Object])
], DefaultComponent);

var _a;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/default.component.js.map

/***/ }),

/***/ "../../../../../src/app/default/index.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__default_component__ = __webpack_require__("../../../../../src/app/default/default.component.ts");
/* harmony namespace reexport (by used) */ __webpack_require__.d(__webpack_exports__, "a", function() { return __WEBPACK_IMPORTED_MODULE_0__default_component__["a"]; });

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/index.js.map

/***/ }),

/***/ "../../../../../src/app/email/contact/contact.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../node_modules/css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, ".myTable td:nth-child(1) {\r\n    width: 20px;\r\n}\r\n\r\n.email-compose{\r\n    min-height: 110vh;\r\n}", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/email/contact/contact.component.html":
/***/ (function(module, exports) {

module.exports = "<p-growl [value]=\"msgs\" sticky=\"sticky\"></p-growl>\r\n\r\n<div class=\"email-compose\">\r\n<form #contactForm=\"ngForm\" *ngIf=\"active\">\r\n\r\n    <div class=\"panel panel-default\" *ngIf=\"!displayViewDialog && !displayCreateDialog\">\r\n        <div class=\"panel-heading\">\r\n            <h3 class=\"panel-title\">Search Contacts</h3>\r\n        </div>\r\n        <div class=\"panel-body\">\r\n            <div class=\"col-md-6\">\r\n                <div class=\"form-group\">\r\n                    <input type=\"text\" placeholder=\"First Name\" class=\"form-control\" id=\"firstName\" [(ngModel)]=\"commonService.contactSearchCriteria.firstName\"\r\n                        name=\"firstNameSearchCriteria\">\r\n                </div>\r\n            </div>\r\n            <div class=\"col-md-6\">\r\n                <div class=\"form-group\">\r\n                    <input type=\"text\" placeholder=\"Last Name\" class=\"form-control\" id=\"lastName\" [(ngModel)]=\"commonService.contactSearchCriteria.lastName\"\r\n                        name=\"lastNameSearchCriteria\">\r\n                </div>\r\n            </div>\r\n            <div class=\"col-md-6\">\r\n                <div class=\"form-group\">\r\n                    <input type=\"email\" placeholder=\"Email\" class=\"form-control\" id=\"email\" [(ngModel)]=\"commonService.contactSearchCriteria.email\"\r\n                        name=\"emailSearchCriteria\">\r\n                </div>\r\n            </div>\r\n            <div class=\"col-md-6\">\r\n                <div class=\"form-group\">\r\n                    <p-multiSelect id=\"groupsSearchCriteria\" defaultLabel=\" Choose Groups - Required\" name=\"groupsSearchCriteria\" [options]=\"commonService.groupNamesForSearch\"\r\n                        (onChange)=\"onSelectItemChange()\" [(ngModel)]=\"commonService.contactSearchCriteria.groupIds\" [style]=\"{'width':'100%'}\"\r\n                        required #groupsSearchCriteria=\"ngModel\"></p-multiSelect>\r\n                    <div [hidden]=\"groupsSearchCriteria.valid || groupsSearchCriteria.pristine\" class=\"alert alert-danger\">\r\n                        Choose atleast one Group\r\n                    </div>\r\n                </div>\r\n            </div>\r\n            <div class=\"col-md-12\">\r\n                <div class=\"col-md-5\"></div>\r\n                <button type=\"submit\" *ngIf=\"authorizationService.isUserHasRole('UI_ACCESS_CONTACTS')\" [disabled]=\"!contactForm.form.valid\"\r\n                    pButton icon=\"fa fa-search\" pButton label=\"Search\" (click)=\"commonService.getAllContactsBySearchCriteria(username)\"></button>\r\n                <button type=\"button\" pButton icon=\"fa fa-refresh\" pButton label=\"Reset\" (click)=\"commonService.resetContactsBySearchCriteria()\"></button>\r\n            </div>\r\n        </div>\r\n    </div>\r\n\r\n    <p-dataTable *ngIf=\"!displayViewDialog && !displayCreateDialog\" [value]=\"commonService.contacts\" [rows]=\"50\" [paginator]=\"true\"\r\n        [pageLinks]=\"3\" [rowsPerPageOptions]=\"[10,20,50]\" selectionMode=\"single\" [responsive]=\"true\" (onRowSelect)=\"onRowSelect($event)\">\r\n        <header>Contacts</header>\r\n        <p-column field=\"firstName\" header=\"First Name\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n        <p-column field=\"lastName\" header=\"Last Name\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n        <p-column field=\"email\" header=\"Email Id\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n        <p-column field=\"groupDetails\" header=\"Groups\" [sortable]=\"true\" [filter]=\"true\" [colspan]=\"2\"></p-column>\r\n        <p-footer>\r\n            <div class=\"row\">\r\n                <div class=\"col-md-12\">\r\n                    <button type=\"button\" icon=\"fa fa-plus\" *ngIf=\"authorizationService.isUserHasRole('UI_CREATE_CONTACTS')\" pButton label=\"Create\"\r\n                        (click)=\"createContactClick()\"></button>\r\n                    <!--button type=\"button\" pButton icon=\"fa fa-download\" label=\"Import\">\r\n\t\t\t\t\t    \r\n\t\t\t\t\t</button-->\r\n                </div>\r\n            </div>\r\n        </p-footer>\r\n    </p-dataTable>\r\n\r\n    <div class=\"panel panel-default\" *ngIf=\"displayViewDialog && contactSelected\">\r\n        <div class=\"panel-heading\">\r\n            <h3 class=\"panel-title\">Contact Details</h3>\r\n        </div>\r\n\r\n        <div class=\"panel-body\">\r\n            <div class=\"row\">\r\n                <div class=\"col-md-6\">\r\n                    <div class=\"form-group\" *ngIf=\"!updateContact\">\r\n                        <label for=\"firstName\">First Name*</label>\r\n                        <input disabled type=\"text\" class=\"form-control\" id=\"firstName\" required [(ngModel)]=\"contactSelected.firstName\" name=\"firstNameView\">\r\n                    </div>\r\n                    <div class=\"form-group\" *ngIf=\"updateContact\">\r\n                        <label for=\"firstName\">First Name*</label>\r\n                        <input type=\"text\" class=\"form-control\" placeholder=\"Required\" id=\"firstName\" required [(ngModel)]=\"contactSelected.firstName\"\r\n                            name=\"firstNameView\" #firstNameView=\"ngModel\">\r\n                        <div [hidden]=\"firstNameView.valid || firstNameView.pristine\" class=\"alert alert-danger\">\r\n                            First name is required\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n                <div class=\"col-md-6\">\r\n                    <div class=\"form-group\" *ngIf=\"!updateContact\">\r\n                        <label for=\"firstName\">Last Name*</label>\r\n                        <input disabled type=\"text\" class=\"form-control\" id=\"lastName\" required [(ngModel)]=\"contactSelected.lastName\" name=\"lastNameView\">\r\n                    </div>\r\n                    <div class=\"form-group\" *ngIf=\"updateContact\">\r\n                        <label for=\"firstName\">Last Name*</label>\r\n                        <input type=\"text\" class=\"form-control\" placeholder=\"Required\" id=\"lastName\" required [(ngModel)]=\"contactSelected.lastName\"\r\n                            name=\"lastNameView\" #lastNameView=\"ngModel\">\r\n                        <div [hidden]=\"lastNameView.valid || lastNameView.pristine\" class=\"alert alert-danger\">\r\n                            Last name is required\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n\r\n            <div class=\"row\">\r\n                <div class=\"col-md-6\">\r\n                    <div class=\"form-group\" *ngIf=\"!updateContact\">\r\n                        <label for=\"email\">Email*</label>\r\n                        <input disabled type=\"text\" class=\"form-control\" id=\"email\" required [(ngModel)]=\"contactSelected.email\" name=\"emailView\">\r\n                    </div>\r\n                    <div class=\"form-group\" *ngIf=\"updateContact\">\r\n                        <label for=\"email\">Email*</label>\r\n                        <input type=\"text\" class=\"form-control\" placeholder=\"Required\" id=\"email\" required [(ngModel)]=\"contactSelected.email\" name=\"emailView\"\r\n                            #emailView=\"ngModel\">\r\n                        <div [hidden]=\"emailView.valid || emailView.pristine\" class=\"alert alert-danger\">\r\n                            Email is required\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n                <div class=\"col-md-6\" *ngIf=\"updateContact && moreGroupItems.length > 0\">\r\n                    <div class=\"form-group\" *ngIf=\"!updateContact\">\r\n                        <label for=\"groups\">Groups</label>\r\n                        <p-multiSelect id=\"groups\" name=\"groups\" [options]=\"moreGroupItems\" [(ngModel)]=\"contactSelected.moreGroups\" [style]=\"{'width':'100%'}\"></p-multiSelect>\r\n                    </div>\r\n                    <div class=\"form-group\" *ngIf=\"updateContact\">\r\n                        <label for=\"groups\">Groups*</label>\r\n                        <p-multiSelect id=\"groups\" required name=\"groupsView\" #groupsView=\"ngModel\" [options]=\"moreGroupItems\" [(ngModel)]=\"contactSelected.moreGroups\"\r\n                            [style]=\"{'width':'100%'}\"></p-multiSelect>\r\n                        <div [hidden]=\"groupsView.valid || groupsView.pristine\" class=\"alert alert-danger\">\r\n                            Group is invalid\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n\r\n            <p-dataTable [value]=\"contactSelected.contactGroups\" *ngIf=\"!updateContact && contactSelected.contactGroups.length > 0\">\r\n                <p-column field=\"group.name\" header=\"Group Name\"></p-column>\r\n                <p-column field=\"active\" header=\"Active\"></p-column>\r\n                <p-column field=\"unSubscribed\" header=\"UnSubscribed\"></p-column>\r\n            </p-dataTable>\r\n            <br/>\r\n            <div class=\"row\">\r\n                <div class=\"col-md-12\">\r\n                    <div class=\"col-md-5\"></div>\r\n                    <button *ngIf=\"!updateContact && authorizationService.isUserHasRole('UI_UPDATE_CONTACTS')\" type=\"button\" pButton icon=\"fa-pencil\" (click)=\"updateContactClick()\"\r\n                        label=\"Edit\"></button>\r\n                    <button *ngIf=\"!updateContact && authorizationService.isUserHasRole('UI_DELETE_CONTACTS')\" type=\"submit\" pButton icon=\"fa-trash\" (click)=\"deleteSelectedContact()\"\r\n                        label=\"Delete\"></button>\r\n                    <button *ngIf=\"!updateContact\" type=\"button\" pButton icon=\"fa-close\" (click)=\"viewDialogCancelClick()\" label=\"Cancel\"></button>\r\n                </div>\r\n            </div>\r\n            <div class=\"panel panel-default\" *ngIf=\"updateContact && contactSelected.contactGroups.length > 0\">\r\n                <div class=\"panel-body\">\r\n                    <table class=\"table\" *ngIf=\"updateContact && contactSelected.contactGroups.length > 0\">\r\n                        <thead>\r\n                            <tr>\r\n                                <th>Group Name</th>\r\n                                <th>First Name</th>\r\n                                <th>Last Name</th>\r\n                                <th>Email</th>\r\n                            </tr>\r\n                        </thead>\r\n                        <tbody>\r\n                            <tr *ngFor=\"let contactGroup of contactSelected.contactGroups\">\r\n                                <td>{{contactGroup.group.name}}</td>\r\n                                <td><input id=\"active\" name=\"active\" type=\"checkbox\" [(ngModel)]=\"contactGroup.active\">Active</td>\r\n                                <td><input id=\"unSubscribed\" name=\"unSubscribed\" type=\"checkbox\" [(ngModel)]=\"contactGroup.unSubscribed\">UnSubscribe</td>\r\n                                <td><input id=\"delete\" name=\"delete\" type=\"checkbox\" disabled=\"true\" [(ngModel)]=\"contactGroup.delete\">Delete</td>\r\n                            </tr>\r\n                        </tbody>\r\n                    </table>\r\n                </div>\r\n            </div>\r\n            <br/>\r\n            <div class=\"row\">\r\n                <div class=\"col-md-12\">\r\n                    <div class=\"col-md-5\"></div>\r\n                    <button *ngIf=\"updateContact && authorizationService.isUserHasRole('UI_CREATE_CONTACTS')\" type=\"submit\" pButton icon=\"fa-check\" (click)=\"updateContactSubmit()\" label=\"Submit\"\r\n                        [disabled]=\"!contactForm.form.valid\"></button>\r\n                    <button *ngIf=\"updateContact\" type=\"button\" pButton icon=\"fa-close\" (click)=\"dialogUpdateCancelClick()\" label=\"Cancel\"></button>\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </div>\r\n\r\n    <div class=\"panel panel-default\" *ngIf=\"displayCreateDialog && contactNew\">\r\n        <div class=\"panel-heading\">\r\n            <h3 class=\"panel-title\">Create Contact</h3>\r\n        </div>\r\n        <div class=\"panel-body\">\r\n            <div class=\"row\">\r\n                <div class=\"col-md-6\">\r\n                    <div class=\"form-group\">\r\n                        <label for=\"firstName\">First Name*</label>\r\n                        <input type=\"text\" class=\"form-control\" id=\"firstName\" required [(ngModel)]=\"contactNew.firstName\" name=\"firstNameCreate\"\r\n                            #firstNameCreate=\"ngModel\" placeholder=\"Required\">\r\n                        <div [hidden]=\"firstNameCreate.valid || firstNameCreate.pristine\" class=\"alert alert-danger\">\r\n                            First name is required\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n                <div class=\"col-md-6\">\r\n                    <div class=\"form-group\">\r\n                        <label for=\"lastName\">Last Name*</label>\r\n                        <input type=\"text\" class=\"form-control\" placeholder=\"Required\" id=\"lastName\" required [(ngModel)]=\"contactNew.lastName\" name=\"lastNameCreate\"\r\n                            #lastNameCreate=\"ngModel\">\r\n                        <div [hidden]=\"lastNameCreate.valid || lastNameCreate.pristine\" class=\"alert alert-danger\">\r\n                            Last name is required\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n\r\n            <div class=\"row\">\r\n                <div class=\"col-md-6\">\r\n                    <div class=\"form-group\">\r\n                        <label for=\"email\" class=\"control-label\">Email*</label>\r\n                        <input type=\"email\" class=\"form-control\" placeholder=\"Required\" id=\"email\" required [(ngModel)]=\"contactNew.email\" name=\"emailCreate\"\r\n                            #emailCreate=\"ngModel\">\r\n                        <div [hidden]=\"emailCreate.valid || emailCreate.pristine\" class=\"alert alert-danger\">\r\n                            Email is invalid\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n                <div class=\"col-md-6\">\r\n                    <div class=\"form-group\">\r\n                        <label for=\"groups\">Groups*</label>\r\n                        <p-multiSelect id=\"groups\" required name=\"groupsCreate\" #groupsCreate=\"ngModel\" [options]=\"commonService.groupItems\" [(ngModel)]=\"contactNew.groups\"\r\n                            [style]=\"{'width':'100%'}\"></p-multiSelect>\r\n                        <div [hidden]=\"groupsCreate.valid || groupsCreate.pristine\" class=\"alert alert-danger\">\r\n                            Group is invalid\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </div>\r\n        <div class=\"row\">\r\n            <div class=\"col-md-12\">\r\n                <div class=\"col-md-5\"></div>\r\n                <button type=\"submit\" pButton icon=\"fa-check\" *ngIf=\"authorizationService.isUserHasRole('UI_CREATE_CONTACTS')\" (click)=\"createContactSubmit()\" label=\"Create\" [disabled]=\"!contactForm.form.valid\"></button>\r\n                <button type=\"button\" pButton icon=\"fa-close\" (click)=\"createDialogCancelClick()\" label=\"Cancel\"></button>\r\n            </div>\r\n        </div>\r\n        <br/>\r\n    </div>\r\n\r\n</form>\r\n\r\n</div>"

/***/ }),

/***/ "../../../../../src/app/email/contact/contact.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__contact_service__ = __webpack_require__("../../../../../src/app/email/contact/contact.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__contact__ = __webpack_require__("../../../../../src/app/email/contact/contact.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__contactgroup_contactgroup__ = __webpack_require__("../../../../../src/app/email/contactgroup/contactgroup.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__group_group__ = __webpack_require__("../../../../../src/app/email/group/group.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__group_group_service__ = __webpack_require__("../../../../../src/app/email/group/group.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__shared_common_service__ = __webpack_require__("../../../../../src/app/email/shared/common.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__core_authorization_service__ = __webpack_require__("../../../../../src/app/core/authorization.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__core_global_service__ = __webpack_require__("../../../../../src/app/core/global.service.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ContactComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};









var ContactComponent = (function () {
    function ContactComponent(contactService, groupService, commonService, authorizationService, globalService) {
        this.contactService = contactService;
        this.groupService = groupService;
        this.commonService = commonService;
        this.authorizationService = authorizationService;
        this.globalService = globalService;
        this.msgs = [];
        this.active = true;
        this.username = globalService.loggedInUser.loggedInUserName;
    }
    ContactComponent.prototype.ngOnInit = function () {
        this.commonService.getAllGroups(this.globalService.loggedInUser.loggedInUserName);
    };
    ContactComponent.prototype.onSelectItemChange = function () {
        var _this = this;
        this.active = false;
        setTimeout(function () { return _this.active = true; }, 0);
        if (this.commonService.contactSearchCriteria.groupIds !== undefined && this.commonService.contactSearchCriteria.groupIds.length <= 0) {
            this.commonService.contactSearchCriteria.groupIds = undefined;
        }
    };
    ContactComponent.prototype.onRowSelect = function (event) {
        var _this = this;
        this.contactSelected = event.data;
        this.contactService.getContactById(this.contactSelected.id)
            .subscribe(function (contactFromDB) {
            _this.contactSelected = contactFromDB;
            _this.displayViewDialog = true;
            _this.updateContact = false;
        });
    };
    ContactComponent.prototype.viewDialogCancelClick = function () {
        this.displayViewDialog = false;
    };
    ContactComponent.prototype.createContactClick = function () {
        var _this = this;
        this.contactNew = new __WEBPACK_IMPORTED_MODULE_2__contact__["a" /* Contact */];
        this.displayCreateDialog = true;
        this.active = false;
        setTimeout(function () { return _this.active = true; }, 0);
    };
    ContactComponent.prototype.createDialogCancelClick = function () {
        this.displayCreateDialog = false;
    };
    ContactComponent.prototype.createContactSubmit = function () {
        var _this = this;
        this.msgs = [];
        var contactGroups = [];
        if (this.contactNew.groups !== undefined) {
            for (var _i = 0, _a = this.contactNew.groups; _i < _a.length; _i++) {
                var group = _a[_i];
                var contactGroup = new __WEBPACK_IMPORTED_MODULE_3__contactgroup_contactgroup__["a" /* ContactGroup */]();
                contactGroup.group = group;
                contactGroup.active = true;
                contactGroup.unSubscribed = false;
                contactGroups.push(contactGroup);
            }
            this.contactNew.contactGroups = contactGroups;
        }
        this.contactService.createContact(this.contactNew)
            .subscribe(function () {
            //this.commonService.getAllContactsBySearchCriteria();
            _this.commonService.resetContactsBySearchCriteria();
            _this.commonService.groups = [];
            _this.displayCreateDialog = false;
            _this.msgs.push({ severity: "info", summary: "Contact created successfully.", detail: "" });
        }, function (error) {
            _this.msgs.push({ severity: "error", summary: "Contact creation failed.", detail: error });
        });
    };
    ContactComponent.prototype.updateContactClick = function () {
        this.updateContact = true;
        this.contactUpdate = this.contactSelected;
        this.contactSelected = this.cloneContact(this.contactUpdate);
        this.moreGroupItems = [];
        this.contactSelected.moreGroups = [];
        for (var _i = 0, _a = this.contactSelected.contactGroups; _i < _a.length; _i++) {
            var contactGroup = _a[_i];
            contactGroup = this.cloneContactGroup(contactGroup);
            contactGroup.group = this.cloneGroup(contactGroup.group);
        }
        for (var _b = 0, _c = this.commonService.groupItems; _b < _c.length; _b++) {
            var groupItem = _c[_b];
            var groupFound = false;
            for (var _d = 0, _e = this.contactSelected.contactGroups; _d < _e.length; _d++) {
                var contactGroup = _e[_d];
                if (contactGroup.group.name === groupItem.label) {
                    groupFound = true;
                }
            }
            if (!groupFound) {
                this.moreGroupItems.push(groupItem);
            }
        }
    };
    ContactComponent.prototype.updateContactSubmit = function () {
        var _this = this;
        this.msgs = [];
        var deleteContactGroups = [];
        for (var _i = 0, _a = this.contactSelected.contactGroups; _i < _a.length; _i++) {
            var contactGroup = _a[_i];
            if (!contactGroup.delete) {
                deleteContactGroups.push(contactGroup);
            }
        }
        this.contactSelected.contactGroups = deleteContactGroups;
        for (var _b = 0, _c = this.contactSelected.moreGroups; _b < _c.length; _b++) {
            var group = _c[_b];
            var contactGroup = new __WEBPACK_IMPORTED_MODULE_3__contactgroup_contactgroup__["a" /* ContactGroup */]();
            contactGroup.group = group;
            contactGroup.active = true;
            contactGroup.unSubscribed = false;
            this.contactSelected.contactGroups.push(contactGroup);
        }
        this.contactService.updateContact(this.contactSelected)
            .subscribe(function (contactUpdated) {
            _this.contactSelected = contactUpdated;
            //this.commonService.getAllContactsBySearchCriteria();
            _this.commonService.resetContactsBySearchCriteria();
            _this.commonService.groups = [];
            _this.displayViewDialog = true;
            _this.updateContact = false;
            _this.msgs.push({ severity: "info", summary: "Contact updated successfully.", detail: "" });
        }, function (error) {
            _this.msgs.push({ severity: "error", summary: "Contact updation failed.", detail: error });
        });
    };
    ContactComponent.prototype.dialogUpdateCancelClick = function () {
        this.updateContact = false;
        this.contactSelected = this.contactUpdate;
    };
    ContactComponent.prototype.deleteSelectedContact = function () {
        var _this = this;
        this.msgs = [];
        this.contactService.deleteContact(this.contactSelected.id)
            .subscribe(function () {
            //this.commonService.getAllContactsBySearchCriteria();
            _this.commonService.resetContactsBySearchCriteria();
            _this.commonService.groups = [];
            _this.msgs.push({ severity: "info", summary: "Contact deleted successfully.", detail: "" });
            _this.displayViewDialog = false;
        }, function (error) {
            _this.msgs.push({ severity: "error", summary: "Contact deletion failed.", detail: error });
        });
    };
    ContactComponent.prototype.cloneContact = function (cont) {
        var contact = new __WEBPACK_IMPORTED_MODULE_2__contact__["a" /* Contact */]();
        for (var prop in cont) {
            contact[prop] = cont[prop];
        }
        return contact;
    };
    ContactComponent.prototype.cloneGroup = function (gro) {
        var group = new __WEBPACK_IMPORTED_MODULE_4__group_group__["a" /* Group */]();
        for (var prop in gro) {
            group[prop] = gro[prop];
        }
        return group;
    };
    ContactComponent.prototype.cloneContactGroup = function (contGrou) {
        var contactGroup = new __WEBPACK_IMPORTED_MODULE_3__contactgroup_contactgroup__["a" /* ContactGroup */]();
        for (var prop in contGrou) {
            contactGroup[prop] = contGrou[prop];
        }
        return contactGroup;
    };
    return ContactComponent;
}());
ContactComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        template: __webpack_require__("../../../../../src/app/email/contact/contact.component.html"),
        styles: [__webpack_require__("../../../../../src/app/email/contact/contact.component.css")]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__contact_service__["a" /* ContactService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__contact_service__["a" /* ContactService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_5__group_group_service__["a" /* GroupService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_5__group_group_service__["a" /* GroupService */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_6__shared_common_service__["a" /* CommonService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_6__shared_common_service__["a" /* CommonService */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_7__core_authorization_service__["a" /* AuthorizationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_7__core_authorization_service__["a" /* AuthorizationService */]) === "function" && _d || Object, typeof (_e = typeof __WEBPACK_IMPORTED_MODULE_8__core_global_service__["a" /* GlobalService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_8__core_global_service__["a" /* GlobalService */]) === "function" && _e || Object])
], ContactComponent);

var _a, _b, _c, _d, _e;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/contact.component.js.map

/***/ }),

/***/ "../../../../../src/app/email/contact/contact.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__("../../../http/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__ = __webpack_require__("../../../../rxjs/Observable.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch__ = __webpack_require__("../../../../rxjs/add/operator/catch.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map__ = __webpack_require__("../../../../rxjs/add/operator/map.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ContactService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var ContactService = (function () {
    function ContactService(http) {
        this.http = http;
        this.contactUrl = "contacts";
    }
    ContactService.prototype.createContact = function (contact) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.post(this.contactUrl, JSON.stringify(contact), { headers: headers })
            .map(function (res) { return; })
            .catch(this.handleError);
    };
    ContactService.prototype.getContactById = function (id) {
        return this.http.get(this.contactUrl + "/" + id)
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    ContactService.prototype.getAllContactsByCriteria = function (contactSearchCriteria, username) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        contactSearchCriteria.username = username;
        return this.http.post(this.contactUrl + "/searchCriteria", JSON.stringify(contactSearchCriteria), { headers: headers })
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    ContactService.prototype.getAllContacts = function (username) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* RequestOptions */]({ headers: headers });
        var urlSearchParams = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["e" /* URLSearchParams */]();
        urlSearchParams.append('username', username);
        var body = urlSearchParams.toString();
        return this.http.post(this.contactUrl + '/getAllByCreatedUser', body, options)
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    ContactService.prototype.updateContact = function (contact) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.put(this.contactUrl + "/" + contact.id, JSON.stringify(contact), { headers: headers })
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    ContactService.prototype.deleteContact = function (objectId) {
        return this.http.delete(this.contactUrl + "/" + objectId)
            .map(function (res) { return; })
            .catch(this.handleError);
    };
    ContactService.prototype.extractData = function (res) {
        var body = res.json();
        return body || {};
    };
    /*private handleError(error: any) {
        let errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg);
        return Observable.throw(errMsg);
    }*/
    ContactService.prototype.handleError = function (error) {
        // In a real world app, we might use a remote logging infrastructure
        //console.log(error);
        var errMsg;
        if (error instanceof __WEBPACK_IMPORTED_MODULE_1__angular_http__["f" /* Response */]) {
            var body = error.json() || '';
            console.log("Raw error : " + error.toString);
            console.log("Body : " + body);
            var err = body.error || JSON.stringify(body);
            errMsg = error.status + " - " + err;
        }
        else {
            errMsg = error.message ? error.message : error.toString();
            console.log("Error not a response : " + errMsg);
        }
        //console.error(errMsg);
        return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].throw(errMsg);
    };
    return ContactService;
}());
ContactService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */]) === "function" && _a || Object])
], ContactService);

var _a;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/contact.service.js.map

/***/ }),

/***/ "../../../../../src/app/email/contact/contact.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__core_model_base_entity__ = __webpack_require__("../../../../../src/app/core/model/base.entity.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Contact; });
var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var Contact = (function (_super) {
    __extends(Contact, _super);
    function Contact() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    return Contact;
}(__WEBPACK_IMPORTED_MODULE_0__core_model_base_entity__["a" /* BaseEntity */]));

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/contact.js.map

/***/ }),

/***/ "../../../../../src/app/email/contact/contact_search_criteria.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ContactSearchCriteria; });
var ContactSearchCriteria = (function () {
    function ContactSearchCriteria() {
    }
    return ContactSearchCriteria;
}());

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/contact_search_criteria.js.map

/***/ }),

/***/ "../../../../../src/app/email/contact/fileupload.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../node_modules/css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "#mainContainer\r\n{\r\n\tbackground:white;\r\n\tpadding:30px;\r\n\tmin-height:50vh;\r\nbox-shadow: -7px 28px 66px -25px rgba(0,0,0,0.75);\r\nborder-radius: 12px 12px 12px 12px;\r\n-moz-border-radius: 12px 12px 12px 12px;\r\n-webkit-border-radius: 12px 12px 12px 12px;\r\nborder: 0px solid #000000;\r\n}\r\n\r\n#rowcontainer {\r\n\tmin-width:90vw;\r\n\tmargin-top:10px;\r\n}\r\n\r\n#description {\r\n\tmargin-left:1%;\r\n}\r\n#upper-container {\r\n\t\r\n\theight:380px;\r\n}\r\n\r\n#description h1 {\r\n\tfont-family: \"Josefin Sans\";\r\n\tfont-size: 24px;\r\n\tfont-style: normal;\r\n\tfont-variant: normal;\r\n\tfont-weight: 500;\r\n\tline-height: 26.4px;\r\n}\r\n#description h3 {\r\n\tfont-family: \"Josefin Sans\";\r\n\tfont-size: 14px;\r\n\tfont-style: normal;\r\n\tfont-variant: normal;\r\n\tfont-weight: 500;\r\n\tline-height: 15.4px;\r\n}\r\n\r\n#description h4, .title-head {\r\n\tfont-family: \"Josefin Sans\";\r\n\tfont-size: 16px;\r\n\tfont-style: normal;\r\n\tfont-variant: normal;\r\n\tfont-weight: bold;\r\n\tline-height: 15.4px;\r\n\tmargin-top:20px;\r\n}\r\n\r\n#description p {\r\n\tfont-family: \"Josefin Sans\";\r\n\tfont-size: 14px;\r\n\tfont-style: normal;\r\n\tfont-variant: normal;\r\n\tfont-weight: 400;\r\n\tline-height: 20px;\r\n\tmargin-top:15px;\r\n}\r\n#description blockquote {\r\n\tfont-family: \"Josefin Sans\";\r\n\tfont-size: 21px;\r\n\tfont-style: normal;\r\n\tfont-variant: normal;\r\n\tfont-weight: 400;\r\n\tline-height: 30px;\r\n}\r\n#description pre {\r\n\tfont-family: \"Josefin Sans\";\r\n\tfont-size: 13px;\r\n\tfont-style: normal;\r\n\tfont-variant: normal;\r\n\tfont-weight: 400;\r\n\tline-height: 18.5714px;\r\n}\r\n\r\n.datagrid table { border-collapse: collapse; text-align: left; width: 100%; } .datagrid {width:60%;font: normal 12px/150% Arial, Helvetica, sans-serif; background: #fff; overflow: hidden; border: 1px solid #006699; border-radius: 3px; }.datagrid table td, .datagrid table th { padding: 3px 10px; }.datagrid table thead th {background:-moz-linear-gradient( center top, #0070A8 5%, #00527A 100% );filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#0070A8', endColorstr='#00527A');background-color:#0070A8; color:#FFFFFF; font-size: 15px; font-weight: bold; border-left: 1px solid #0070A8; } .datagrid table thead th:first-child { border: none; }.datagrid table tbody td { color: #00557F; border-left: 1px solid #E1EEF4;font-size: 12px;font-weight: normal; }.datagrid table tbody .alt td { background: #E1EEf4; color: #00557F; }.datagrid table tbody td:first-child { border-left: none; }.datagrid table tbody tr:last-child td { border-bottom: none; }.datagrid table tfoot td div { border-top: 1px solid #006699;background: #E1EEf4;} .datagrid table tfoot td { padding: 0; font-size: 12px } .datagrid table tfoot td div{ padding: 2px; }.datagrid table tfoot td ul { margin: 0; padding:0; list-style: none; text-align: right; }.datagrid table tfoot  li { display: inline; }.datagrid table tfoot li a { text-decoration: none; display: inline-block;  padding: 2px 8px; margin: 1px;color: #FFFFFF;border: 1px solid #006699; border-radius: 3px;background:-moz-linear-gradient( center top, #006699 5%, #00557F 100% );filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#006699', endColorstr='#00557F');background-color:#006699; }.datagrid table tfoot ul.active, .datagrid table tfoot ul a:hover { text-decoration: none;border-color: #00557F; color: #FFFFFF; background: none; background-color:#006699;}div.dhtmlx_window_active, div.dhx_modal_cover_dv { position: fixed !important; }", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/email/contact/fileupload.component.html":
/***/ (function(module, exports) {

module.exports = "<p-growl [value]=\"msgs\" sticky=\"sticky\"></p-growl>\r\n<div id=\"mainContainer\" style=\"height:110vh;\">\r\n<nav id=\"upper-container\" class=\"navbar navbar-default\">\r\n                    <div class=\"container-fluid\">\r\n                        <div class=\"navbar-header\">\r\n                        <div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\r\n                            <ul class=\"nav navbar-nav\">\r\n                            <li><p class=\"title-head\">Bulk Contact Upload</p></li>\r\n                            </ul>\t\t\t\t\t\t\t\r\n                        </div>\r\n\t\t\t\t\t\t<div id=\"description\">\r\n\t\t\t\t\t\t\t<p>This module allows you to upload your contact list in bulk using a CSV file. Please note that only CSV files are allowed to be used as the source of upload. Files in any other\r\n\t\t\t\t\t\t\tformat will not be accepted. The data in the file should map the following format:\r\n\t\t\t\t\t\t\tfirstName,lastName,email,groupName\r\n\t\t\t\t\t\t\tYou can also set in a new group name in the file if you wish your contact to map to the new group. The application will then insert a new record for the group and map your contact to that group.\r\n\t\t\t\t\t\t\tYou can also map your contact to multiple groups. To map a contact to multiple groups, enter the group names separated by a semi-colon (;). The application uses the semi-colon delimiter to identify \r\n\t\t\t\t\t\t\tmultiple groups for a contact. Pleae check the examples below for usage.\r\n\t\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t\t\r\n\t\t\t\t\t\t\t<h4>Usage (Expected File data)</h4>\r\n\t\t\t\t\t\t\t<p>To map a contact with first name \"A\", last name \"B\", email as \"C@emailsample.com\" and group \"D\", the file should have the following entry:</p>\r\n\t\t\t\t\t\t\t\t<div class=\"datagrid\">\r\n\t\t\t\t\t\t\t\t\t<table>\r\n\t\t\t\t\t\t\t\t\t\t<thead>\r\n\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<th>First Name</th>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<th>Last Name</th>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<th>Email</th>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<th>Group</th>\r\n\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t\t\t\t\t</thead>\r\n\t\t\t\t\t\t\t\t\t\t<tbody>\r\n\t\t\t\t\t\t\t\t\t\t\t<tr class=\"alt\">\r\n\t\t\t\t\t\t\t\t\t\t\t\t<td>A</td>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<td>B</td>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<td>C@emailsample.com</td>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<td>D</td>\r\n\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t\t\t\t\t</tbody>\r\n\t\t\t\t\t\t\t\t\t</table>\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t<p>To map a contact with first name \"A\", last name \"B\", email as \"C@emailsample.com\" and multiple groups \"D,E,F\", the file should have the following entry:</p>\r\n\t\t\t\t\t\t\t\t<div class=\"datagrid\">\r\n\t\t\t\t\t\t\t\t\t<table>\r\n\t\t\t\t\t\t\t\t\t\t<thead>\r\n\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<th>First Name</th>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<th>Last Name</th>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<th>Email</th>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<th>Group</th>\r\n\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t\t\t\t\t</thead>\r\n\t\t\t\t\t\t\t\t\t\t<tbody>\r\n\t\t\t\t\t\t\t\t\t\t\t<tr class=\"alt\">\r\n\t\t\t\t\t\t\t\t\t\t\t\t<td>A</td>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<td>B</td>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<td>C@emailsample.com</td>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<td>D;E;F</td>\r\n\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t\t\t\t\t</tbody>\r\n\t\t\t\t\t\t\t\t\t</table>\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t</div>\r\n                        </div>\r\n                    </div>\r\n                </nav>\r\n                <div id=\"rowcontainer\" class=\"container\">\r\n                    <div class=\"row\">\r\n\t\t\t\t\t<div class=\"col-md-2\" style=\"padding-top:7vh;padding-left:2vh;\">\r\n\t\t\t\t\t\t<img src=\"../../../app/resources/images/bulkuploadmainpage.png\" style=\"width:150px;height:150px;\"/>\r\n\t\t\t\t\t</div>\r\n                        <div class=\"col-md-4\">\r\n\t\t\t\t\t\t\t<form>\r\n                                <div class=\"form-group\">\r\n                                    <label for=\"single\">Select a CSV file to upload</label>\r\n                                    <input type=\"file\" ng2FileSelect [uploader]=\"uploader\" name=\"payload\" />\r\n                                </div>          \r\n                            </form>\r\n\t\t\t\t\t\t\t \r\n                        </div>\r\n                        <div class=\"col-md-8\">\r\n                            <h3>Bulk Upload your contact list from a csv file</h3>\r\n                            Queue length: {{ uploader?.queue?.length }}\r\n\r\n                            <table class=\"table\">\r\n                                <thead>\r\n                                <tr>\r\n                                    <th width=\"50%\">Name</th>\r\n                                    <th>Size</th>\r\n                                    <th>Progress</th>\r\n                                    <th>Status</th>\r\n                                    <th>Actions</th>\r\n                                </tr>\r\n                                </thead>\r\n                                <tbody>\r\n                                <tr *ngFor=\"let item of uploader.queue\">\r\n                                    <td><strong>{{ item.file.name }}</strong></td>\r\n                                    <td nowrap>{{ item.file.size/1024/1024 | number:'.2' }} MB</td>\r\n                                    <td>\r\n                                        <div class=\"progress\" style=\"margin-bottom: 0;\">\r\n                                            <div class=\"progress-bar\" role=\"progressbar\" [ngStyle]=\"{ 'width': item.progress + '%' }\"></div>\r\n                                        </div>\r\n                                    </td>\r\n                                    <td class=\"text-center\">\r\n                                        <span *ngIf=\"item.isSuccess\"><i class=\"glyphicon glyphicon-ok\"></i></span>\r\n                                        <span *ngIf=\"item.isCancel\"><i class=\"glyphicon glyphicon-ban-circle\"></i></span>\r\n                                        <span *ngIf=\"item.isError\"><i class=\"glyphicon glyphicon-remove\"></i></span>\r\n                                    </td>\r\n                                    <td nowrap>\r\n                                        <button type=\"button\" class=\"btn btn-success btn-s\"\r\n                                                (click)=\"item.upload()\" [disabled]=\"item.isReady || item.isUploading || item.isSuccess\">\r\n                                            <span class=\"glyphicon glyphicon-upload\"></span> Upload\r\n                                        </button>\r\n                                        <button type=\"button\" class=\"btn btn-warning btn-s\"\r\n                                                (click)=\"item.cancel()\" [disabled]=\"!item.isUploading\">\r\n                                            <span class=\"glyphicon glyphicon-ban-circle\"></span> Cancel\r\n                                        </button>\r\n                                        <!--button type=\"button\" class=\"btn btn-danger btn-xs\"\r\n                                                (click)=\"item.remove()\">\r\n                                            <span class=\"glyphicon glyphicon-trash\"></span> Remove\r\n                                        </button-->\r\n\t\t\t\t\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-danger btn-s\"\r\n\t\t\t\t\t\t\t\t\t\t\t\t(click)=\"uploader.clearQueue()\" [disabled]=\"!uploader.queue.length\">\r\n\t\t\t\t\t\t\t\t\t\t\t<span class=\"glyphicon glyphicon-trash\"></span> Remove\r\n\t\t\t\t\t\t\t\t\t\t</button>\r\n                                    </td>\r\n                                </tr>\r\n                                </tbody>\r\n                            </table>\r\n\r\n                            <div>\r\n                                <div>\r\n                                    Upload progress:\r\n                                    <div class=\"progress\" style=\"\">\r\n                                        <div class=\"progress-bar\" role=\"progressbar\" [ngStyle]=\"{ 'width': uploader.progress + '%' }\"></div>\r\n                                    </div>\r\n                                </div>\r\n                                <!--button type=\"button\" class=\"btn btn-success btn-s\"\r\n                                        (click)=\"uploader.uploadAll()\" [disabled]=\"!uploader.getNotUploadedItems().length\">\r\n                                    <span class=\"glyphicon glyphicon-upload\"></span> Upload all\r\n                                </button>\r\n                                <button type=\"button\" class=\"btn btn-warning btn-s\"\r\n                                        (click)=\"uploader.cancelAll()\" [disabled]=\"!uploader.isUploading\">\r\n                                    <span class=\"glyphicon glyphicon-ban-circle\"></span> Cancel all\r\n                                </button>\r\n                                <button type=\"button\" class=\"btn btn-danger btn-s\"\r\n                                        (click)=\"uploader.clearQueue()\" [disabled]=\"!uploader.queue.length\">\r\n                                    <span class=\"glyphicon glyphicon-trash\"></span> Remove all\r\n                                </button-->\r\n                            </div>\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n\t\t\t\t</div>\r\n\t\t\t\t    <p-dialog header=\"Status...\" [(visible)]=\"uploaded\"  modal=\"modal\" (onAfterShow)=\"uploadContact()\" minWidth=\"200px\">\r\n\t\t\t\t\t</p-dialog>"

/***/ }),

/***/ "../../../../../src/app/email/contact/fileupload.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ng2_file_upload__ = __webpack_require__("../../../../ng2-file-upload/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_ng2_file_upload___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_ng2_file_upload__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return FileUploadComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var FileUploadComponent = (function () {
    function FileUploadComponent() {
        var _this = this;
        this.msgs = [];
        this.uploaded = false;
        this.uploadContact();
        this.uploader.onCompleteItem = function (item, response, status, header) {
            if (status === 200) {
                _this.msgs.push({ severity: "info", summary: "Contacts uploaded successfully", detail: response });
                _this.uploaded = false;
            }
            else {
                _this.msgs.push({ severity: "error", summary: "Upload failed", detail: response });
                _this.uploaded = false;
            }
        };
    }
    FileUploadComponent.prototype.uploadContact = function () {
        this.uploader = new __WEBPACK_IMPORTED_MODULE_1_ng2_file_upload__["FileUploader"]({ url: 'contacts/bulkupload' });
    };
    FileUploadComponent.prototype.showDialog = function () {
        this.uploaded = true;
    };
    return FileUploadComponent;
}());
FileUploadComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'upload',
        template: __webpack_require__("../../../../../src/app/email/contact/fileupload.component.html"),
        styles: [__webpack_require__("../../../../../src/app/email/contact/fileupload.component.css")]
    }),
    __metadata("design:paramtypes", [])
], FileUploadComponent);

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/fileupload.component.js.map

/***/ }),

/***/ "../../../../../src/app/email/contactgroup/contactgroup.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__("../../../http/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__ = __webpack_require__("../../../../rxjs/Observable.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch__ = __webpack_require__("../../../../rxjs/add/operator/catch.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map__ = __webpack_require__("../../../../rxjs/add/operator/map.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ContactGroupService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var ContactGroupService = (function () {
    function ContactGroupService(http) {
        this.http = http;
        this.contactgroupUrl = "contactgroups";
    }
    ContactGroupService.prototype.getAllContactGroups = function () {
        return this.http.get(this.contactgroupUrl)
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    ContactGroupService.prototype.updateContactGroup = function (contactGroup) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.put(this.contactgroupUrl, JSON.stringify(contactGroup), { headers: headers })
            .map(function (res) { return; })
            .catch(this.handleError);
    };
    ContactGroupService.prototype.deleteContactGroup = function (contactGroup) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.delete(this.contactgroupUrl + "/" + contactGroup.contact.id + "/" + contactGroup.group.id, { headers: headers })
            .map(function (res) { return; })
            .catch(this.handleError);
    };
    ContactGroupService.prototype.extractData = function (res) {
        var body = res.json();
        return body || {};
    };
    ContactGroupService.prototype.handleError = function (error) {
        var errMsg = (error.message) ? error.message :
            error.status ? error.status + " - " + error.statusText : 'Server error';
        console.error(errMsg);
        return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].throw(errMsg);
    };
    return ContactGroupService;
}());
ContactGroupService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */]) === "function" && _a || Object])
], ContactGroupService);

var _a;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/contactgroup.service.js.map

/***/ }),

/***/ "../../../../../src/app/email/contactgroup/contactgroup.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ContactGroup; });
var ContactGroup = (function () {
    function ContactGroup() {
    }
    return ContactGroup;
}());

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/contactgroup.js.map

/***/ }),

/***/ "../../../../../src/app/email/email.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common__ = __webpack_require__("../../../common/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_forms__ = __webpack_require__("../../../forms/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_http__ = __webpack_require__("../../../http/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__angular_material__ = __webpack_require__("../../../material/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__group_group_component__ = __webpack_require__("../../../../../src/app/email/group/group.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__contact_contact_component__ = __webpack_require__("../../../../../src/app/email/contact/contact.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__contact_fileupload_component__ = __webpack_require__("../../../../../src/app/email/contact/fileupload.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__email_email_component__ = __webpack_require__("../../../../../src/app/email/email/email.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__server_emailserver_component__ = __webpack_require__("../../../../../src/app/email/server/emailserver.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__group_group_service__ = __webpack_require__("../../../../../src/app/email/group/group.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__shared_common_service__ = __webpack_require__("../../../../../src/app/email/shared/common.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__contact_contact_service__ = __webpack_require__("../../../../../src/app/email/contact/contact.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__contactgroup_contactgroup_service__ = __webpack_require__("../../../../../src/app/email/contactgroup/contactgroup.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14__server_emailserver_service__ = __webpack_require__("../../../../../src/app/email/server/emailserver.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15__email_email_service__ = __webpack_require__("../../../../../src/app/email/email/email.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_16__server_emailserverproperties_service__ = __webpack_require__("../../../../../src/app/email/server/emailserverproperties.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_17_ng2_file_upload__ = __webpack_require__("../../../../ng2-file-upload/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_17_ng2_file_upload___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_17_ng2_file_upload__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_18_angular_froala_wysiwyg__ = __webpack_require__("../../../../angular-froala-wysiwyg/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_19_primeng_primeng__ = __webpack_require__("../../../../primeng/primeng.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_19_primeng_primeng___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_19_primeng_primeng__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return EmailModule; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};


















// Import Angular2 plugin.


var EmailModule = (function () {
    function EmailModule() {
    }
    return EmailModule;
}());
EmailModule = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["NgModule"])({
        imports: [__WEBPACK_IMPORTED_MODULE_1__angular_common__["CommonModule"], __WEBPACK_IMPORTED_MODULE_2__angular_forms__["FormsModule"], __WEBPACK_IMPORTED_MODULE_19_primeng_primeng__["EditorModule"], __WEBPACK_IMPORTED_MODULE_3__angular_http__["a" /* HttpModule */], __WEBPACK_IMPORTED_MODULE_3__angular_http__["b" /* JsonpModule */], __WEBPACK_IMPORTED_MODULE_4__angular_material__["a" /* MaterialModule */].forRoot(), __WEBPACK_IMPORTED_MODULE_19_primeng_primeng__["DataTableModule"], __WEBPACK_IMPORTED_MODULE_19_primeng_primeng__["ButtonModule"],
            __WEBPACK_IMPORTED_MODULE_19_primeng_primeng__["DialogModule"], __WEBPACK_IMPORTED_MODULE_19_primeng_primeng__["PanelModule"], __WEBPACK_IMPORTED_MODULE_19_primeng_primeng__["SharedModule"], __WEBPACK_IMPORTED_MODULE_19_primeng_primeng__["GrowlModule"], __WEBPACK_IMPORTED_MODULE_19_primeng_primeng__["MultiSelectModule"], __WEBPACK_IMPORTED_MODULE_19_primeng_primeng__["ListboxModule"], __WEBPACK_IMPORTED_MODULE_19_primeng_primeng__["TabViewModule"], __WEBPACK_IMPORTED_MODULE_19_primeng_primeng__["DropdownModule"], __WEBPACK_IMPORTED_MODULE_18_angular_froala_wysiwyg__["a" /* FroalaEditorModule */].forRoot(), __WEBPACK_IMPORTED_MODULE_18_angular_froala_wysiwyg__["b" /* FroalaViewModule */].forRoot()],
        declarations: [__WEBPACK_IMPORTED_MODULE_5__group_group_component__["a" /* GroupComponent */], __WEBPACK_IMPORTED_MODULE_6__contact_contact_component__["a" /* ContactComponent */], __WEBPACK_IMPORTED_MODULE_9__server_emailserver_component__["a" /* EmailServerComponent */], __WEBPACK_IMPORTED_MODULE_8__email_email_component__["a" /* EmailComponent */], __WEBPACK_IMPORTED_MODULE_7__contact_fileupload_component__["a" /* FileUploadComponent */], __WEBPACK_IMPORTED_MODULE_17_ng2_file_upload__["FileSelectDirective"], __WEBPACK_IMPORTED_MODULE_17_ng2_file_upload__["FileDropDirective"]],
        providers: [__WEBPACK_IMPORTED_MODULE_12__contact_contact_service__["a" /* ContactService */], __WEBPACK_IMPORTED_MODULE_10__group_group_service__["a" /* GroupService */], __WEBPACK_IMPORTED_MODULE_11__shared_common_service__["a" /* CommonService */], __WEBPACK_IMPORTED_MODULE_13__contactgroup_contactgroup_service__["a" /* ContactGroupService */], __WEBPACK_IMPORTED_MODULE_14__server_emailserver_service__["a" /* EmailServerService */], __WEBPACK_IMPORTED_MODULE_15__email_email_service__["a" /* EmailService */], __WEBPACK_IMPORTED_MODULE_16__server_emailserverproperties_service__["a" /* EmailServerPropertiesService */]]
    })
], EmailModule);

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/email.module.js.map

/***/ }),

/***/ "../../../../../src/app/email/email/email.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../node_modules/css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, " .class1 tbody tr:nth-child(2n) {\r\n    background: #f9f9f9;\r\n  }\r\n \r\n .class2 thead tr th, .class2 tbody tr td  {\r\n    border-style: dashed;\r\n }\r\n \r\n .defaultBorderBackground thead th td, .defaultBorderBackground tbody tr td {\r\n\tborder:1px solid #E4E6E9;\r\n }\r\n \r\n .defaultBorderBackground thead th {\r\n\t background:#E6E6E6;\r\n }\r\n \r\n .defaultBorderBackground thead th, .defaultBorderBackground tbody tr {\r\n\t min-height:70px;\r\n }", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/email/email/email.component.html":
/***/ (function(module, exports) {

module.exports = "<p-growl [value]=\"msgs\" sticky=\"true\"></p-growl>\r\n\r\n<div class=\"main-panel\" style=\"min-height:110vh;\">\r\n<form #emailForm=\"ngForm\" *ngIf=\"active\" >\r\n\r\n    <div class=\"panel panel-default\">\r\n        <div class=\"col-md-5\"></div>\r\n        <div class=\"panel-heading\">\r\n            <h3 class=\"panel-title\">Email Campaign</h3>\r\n        </div>\r\n        <div class=\"panel-body\">\r\n            <div class=\"row\">\r\n                <div class=\"col-md-6\">\r\n                    <div class=\"form-group\">\r\n                        <label for=\"groups\">Groups*</label> \r\n                        <p-multiSelect required id=\"groups\" name=\"emailGroups\" [options]=\"commonService.groupItems\" [(ngModel)]=\"selectedGroups\" [style]=\"{'width':'100%'}\"\r\n                            #emailGroups=\"ngModel\"></p-multiSelect>\r\n                        <div [hidden]=\"emailGroups.valid || emailGroups.pristine\" class=\"alert alert-danger\">\r\n                            Group is required\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n            <div class=\"row\">\r\n                <div class=\"col-md-12\">\r\n                    <div class=\"form-group\">\r\n                        <label for=\"subject\">Reply To</label>\r\n                        <input type=\"text\" placeholder=\"Reply To\" class=\"form-control\" id=\"replyTo\" required [(ngModel)]=\"emailVO.replyTo\" name=\"replyTo\"\r\n                            #replyTo=\"ngModel\" pattern=\"^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$\">\r\n                        <!--div [hidden]=\"subject.valid || subject.pristine\" class=\"alert alert-danger\">\r\n                            Subject is required\r\n                        </div-->\r\n                    </div>\r\n                </div>\r\n            </div>            \r\n            <div class=\"row\">\r\n                <div class=\"col-md-12\">\r\n                    <div class=\"form-group\">\r\n                        <label for=\"subject\">Subject*</label>\r\n                        <input type=\"text\" placeholder=\"Required\" class=\"form-control\" id=\"subject\" required [(ngModel)]=\"emailVO.subject\" name=\"subject\"\r\n                            #subject=\"ngModel\">\r\n                        <div [hidden]=\"subject.valid || subject.pristine\" class=\"alert alert-danger\">\r\n                            Subject is required\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n            <div class=\"row\">\r\n                <div class=\"col-md-12\" style=\"min-height:400px;\">\r\n                    <label for=\"message\">Message*</label>\r\n                    <!--textarea class=\"form-control\" placeholder=\"Required\" rows=\"10\" id=\"message\" required name=\"message\" [(ngModel)]=\"emailVO.message\"\r\n                        #message=\"ngModel\"></textarea-->\r\n                    <!--p-editor [(ngModel)]=\"emailVO.message\" [style]=\"{'height':'320px'}\" placeholder=\"Required\" #message=\"ngModel\" name=\"message\" required #message=\"ngModel\">\r\n                    </p-editor-->\r\n                    <textarea style=\"min-height:50vh;\" [froalaEditor]=\"options\" [(froalaModel)]=\"emailVO.message\" name=\"message\"></textarea>   \r\n                    <!--div [hidden]=\"message.valid || message.pristine\" class=\"alert alert-danger\">\r\n                        Message is required\r\n                    </div-->\r\n                </div>\r\n            </div>\r\n            <br/>\r\n            <div class=\"row\">\r\n                <div class=\"col-md-12\">\r\n                    <button type=\"submit\" pButton icon=\"fa-check\" (click)=\"showDialog()\" label=\"Send\" [disabled]=\"!emailForm.form.valid\"></button>\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </div>\r\n\r\n    <p-dialog header=\"Sending Mail...\" [(visible)]=\"emailSending\" modal=\"modal\" (onAfterShow)=\"sendEmail()\" minWidth=\"200px\">\r\n    </p-dialog>\r\n\r\n</form>\r\n\r\n</div>"

/***/ }),

/***/ "../../../../../src/app/email/email/email.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__email__ = __webpack_require__("../../../../../src/app/email/email/email.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__shared_common_service__ = __webpack_require__("../../../../../src/app/email/shared/common.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__core_global_service__ = __webpack_require__("../../../../../src/app/core/global.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__email_service__ = __webpack_require__("../../../../../src/app/email/email/email.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__error__ = __webpack_require__("../../../../../src/app/email/email/error.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return EmailComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var EmailComponent = (function () {
    function EmailComponent(emailService, commonService, globalService) {
        this.emailService = emailService;
        this.commonService = commonService;
        this.globalService = globalService;
        this.emailVO = new __WEBPACK_IMPORTED_MODULE_1__email__["a" /* Email */]();
        this.active = true;
        this.msgs = [];
        this.emailSending = false;
        this.options = {
            placeholderText: 'Compose your email here!',
            charCounterCount: false,
            toolbarButtons: [
                'fullscreen', 'bold', 'italic', 'underline', 'strikeThrough', 'subscript', 'superscript', '|', 'fontFamily', 'fontSize', 'color', 'inlineStyle', 'paragraphStyle', '|',
                'paragraphFormat', 'align', 'formatOL', 'formatUL', 'outdent', 'indent', 'quote', '-', 'insertLink', 'insertImage', 'insertTable', 'insertFile', '|', 'specialCharacters', 'insertHR',
                'selectAll', 'clearFormatting', '|', 'spellChecker', 'help', 'html', '|', 'undo', 'redo'
            ],
            fileUploadURL: '/emails/attachFile',
            fileUploadParams: {
                id: this.globalService.loggedInUser.loggedInUserName
            },
            imageUploadURL: '/emails/attachFile',
            imageUploadParams: {
                id: this.globalService.loggedInUser.loggedInUserName
            },
            imageUploadMethod: 'PUT',
            fileUploadMethod: 'PUT',
            // Set max file size to 20MB.
            fileMaxSize: 20 * 1024 * 1024,
            // Allow to upload any file.
            fileAllowedTypes: ['*'],
            tableStyles: {
                defaultBorderBackground: 'Default'
            },
            events: {
                'froalaEditor.file.error': function (e, editor, error, response) {
                    var errorMsg = new __WEBPACK_IMPORTED_MODULE_5__error__["a" /* Error */]();
                    //console.log("Error during file upload : "+error.code+" | "+error.message);
                    if (error.code === 5) {
                        error.message = "File exceeds max size limit of 20MB";
                        alert("File exceeds max size limit of 20MB");
                    }
                    else if (error.code === 3) {
                        error.message = response;
                        this.error = response;
                        errorMsg = JSON.parse(response);
                        alert(errorMsg.error);
                    }
                    return error;
                }
            }
        };
    }
    EmailComponent.prototype.ngOnInit = function () {
        this.commonService.getAllGroups(this.globalService.loggedInUser.loggedInUserName);
    };
    EmailComponent.prototype.test = function () {
        alert(this.selectedGroups.length);
    };
    EmailComponent.prototype.sendEmail = function () {
        var _this = this;
        this.emailVO.groupIdList = [];
        for (var _i = 0, _a = this.selectedGroups; _i < _a.length; _i++) {
            var group = _a[_i];
            this.emailVO.groupIdList.push(group.id);
        }
        this.emailService.sendEmail(this.emailVO)
            .subscribe(function () {
            _this.emailVO = new __WEBPACK_IMPORTED_MODULE_1__email__["a" /* Email */]();
            _this.selectedGroups;
            _this.active = false;
            setTimeout(function () { return _this.active = true; }, 0);
            _this.msgs.push({ severity: "info", summary: "Email campaign triggered successully.",
                detail: "Based on the number of contacts targeted, the campaign might take longer to complete. Visit your dashboard to see the progress of the Jobs." });
            _this.emailSending = false;
        }, function (error) {
            _this.msgs.push({ severity: "error", summary: "Email sending failed.", detail: error });
            _this.emailSending = false;
        });
    };
    EmailComponent.prototype.showDialog = function () {
        this.emailSending = true;
    };
    EmailComponent.prototype.cancelClick = function () {
        this.emailVO = new __WEBPACK_IMPORTED_MODULE_1__email__["a" /* Email */]();
    };
    return EmailComponent;
}());
EmailComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        template: __webpack_require__("../../../../../src/app/email/email/email.component.html"),
        styles: [__webpack_require__("../../../../../src/app/email/email/table.min.css"), __webpack_require__("../../../../../src/app/email/email/email.component.css")]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_4__email_service__["a" /* EmailService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__email_service__["a" /* EmailService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__shared_common_service__["a" /* CommonService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__shared_common_service__["a" /* CommonService */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_3__core_global_service__["a" /* GlobalService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__core_global_service__["a" /* GlobalService */]) === "function" && _c || Object])
], EmailComponent);

var _a, _b, _c;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/email.component.js.map

/***/ }),

/***/ "../../../../../src/app/email/email/email.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__("../../../http/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__ = __webpack_require__("../../../../rxjs/Observable.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return EmailService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var EmailService = (function () {
    function EmailService(http) {
        this.http = http;
        this.emailUrl = "emails";
    }
    EmailService.prototype.sendEmail = function (emailVo) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.post(this.emailUrl, JSON.stringify(emailVo), { headers: headers })
            .map(function (res) { return; })
            .catch(this.handleError);
    };
    EmailService.prototype.handleError = function (error) {
        var errMsg;
        if (error instanceof __WEBPACK_IMPORTED_MODULE_1__angular_http__["f" /* Response */]) {
            var body = error.json() || '';
            var err = body.error || JSON.stringify(body);
            errMsg = error.status + " - " + err;
        }
        else {
            errMsg = error.message ? error.message : error.toString();
        }
        //console.error(errMsg);
        return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].throw(errMsg);
    };
    return EmailService;
}());
EmailService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */]) === "function" && _a || Object])
], EmailService);

var _a;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/email.service.js.map

/***/ }),

/***/ "../../../../../src/app/email/email/email.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Email; });
var Email = (function () {
    function Email() {
    }
    return Email;
}());

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/email.js.map

/***/ }),

/***/ "../../../../../src/app/email/email/error.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Error; });
var Error = (function () {
    function Error() {
    }
    return Error;
}());

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/error.js.map

/***/ }),

/***/ "../../../../../src/app/email/email/table.min.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../node_modules/css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "/*!\n * froala_editor v2.6.4 (https://www.froala.com/wysiwyg-editor)\n * License https://froala.com/wysiwyg-editor/terms/\n * Copyright 2014-2017 Froala Labs\n */\n\n.clearfix::after{clear:both;display:block;content:\"\";height:0}.hide-by-clipping{position:absolute;width:1px;height:1px;padding:0;margin:-1px;overflow:hidden;clip:rect(0,0,0,0);border:0}.fr-element table td.fr-selected-cell,.fr-element table th.fr-selected-cell{border:1px double #1e88e5}.fr-element table tr{user-select:none;-o-user-select:none;-moz-user-select:none;-khtml-user-select:none;-webkit-user-select:none;-ms-user-select:none}.fr-element table td,.fr-element table th{user-select:text;-o-user-select:text;-moz-user-select:text;-khtml-user-select:text;-webkit-user-select:text;-ms-user-select:text}.fr-element .fr-no-selection table td,.fr-element .fr-no-selection table th{user-select:none;-o-user-select:none;-moz-user-select:none;-khtml-user-select:none;-webkit-user-select:none;-ms-user-select:none}.fr-table-resizer{cursor:col-resize;position:fixed;z-index:3;display:none}.fr-table-resizer.fr-moving{z-index:2}.fr-table-resizer div{-webkit-opacity:0;-moz-opacity:0;opacity:0;-ms-filter:\"alpha(Opacity=0)\";border-right:1px solid #1e88e5}.fr-no-selection{user-select:none;-o-user-select:none;-moz-user-select:none;-khtml-user-select:none;-webkit-user-select:none;-ms-user-select:none}.fr-popup .fr-table-size .fr-table-size-info{text-align:center;font-size:14px;padding:8px}.fr-popup .fr-table-size .fr-select-table-size{line-height:0;padding:0 5px 5px;white-space:nowrap}.fr-popup .fr-table-size .fr-select-table-size>span{display:inline-block;padding:0 4px 4px 0;background:0 0}.fr-popup .fr-table-size .fr-select-table-size>span>span{display:inline-block;width:18px;height:18px;border:1px solid #ddd}.fr-popup .fr-table-size .fr-select-table-size>span.hover{background:0 0}.fr-popup .fr-table-size .fr-select-table-size>span.hover>span{background:rgba(30,136,229,.3);border:solid 1px #1e88e5}.fr-popup .fr-table-size .fr-select-table-size .new-line::after{clear:both;display:block;content:\"\";height:0}.fr-popup.fr-above .fr-table-size .fr-select-table-size>span{display:inline-block!important}.fr-popup .fr-table-colors-buttons{margin-bottom:5px}.fr-popup .fr-table-colors{line-height:0;display:block}.fr-popup .fr-table-colors>span{display:inline-block;width:32px;height:32px;position:relative;z-index:1}.fr-popup .fr-table-colors>span>i{text-align:center;line-height:32px;height:32px;width:32px;font-size:13px;position:absolute;bottom:0;cursor:default;left:0}.fr-popup .fr-table-colors>span:focus{outline:1px solid #222;z-index:2}.fr-popup.fr-desktop .fr-table-size .fr-select-table-size>span>span{width:12px;height:12px}.fr-insert-helper{position:fixed;z-index:9999;white-space:nowrap}", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/email/group/group.component.html":
/***/ (function(module, exports) {

module.exports = "<p-growl [value]=\"msgs\"></p-growl>\r\n\r\n<div class=\"main-panel\" style=\"min-height:110vh;\">\r\n<form *ngIf=\"active\" #groupForm=\"ngForm\">\r\n\r\n    <div class=\"panel panel-default\" *ngIf=\"!displayViewDialog && !displayCreateDialog\">\r\n        <div class=\"panel-heading\">\r\n            <h3 style=\"margin:0px auto;display:block\" class=\"panel-title\">Search Groups</h3>\r\n        </div>\r\n        <div class=\"panel-body\">\r\n            <div class=\"col-md-6\">\r\n                <div class=\"form-group\">\r\n                    <input type=\"text\" placeholder=\"Group Name\" class=\"form-control\" id=\"groupName\" [(ngModel)]=\"commonService.groupSearchCriteria.name\"\r\n                        name=\"groupNameSearchCriteria\">\r\n                </div>\r\n            </div>\r\n            <div class=\"col-md-6\">               \r\n                <button type=\"submit\" pButton icon=\"fa fa-search\" pButton label=\"Search\" *ngIf=\"authorizationService.isUserHasRole('UI_ACCESS_GROUPS')\" (click)=\"commonService.searchGroupsByCriteria(username)\"></button>\r\n                <button type=\"button\" pButton icon=\"fa fa-refresh\" pButton label=\"Reset\" (click)=\"commonService.resetGroupsBySearchCriteria()\"></button>\r\n            </div>\r\n        </div>\r\n    </div>\r\n\r\n    <p-dataTable [value]=\"commonService.groups\" *ngIf=\"!displayViewDialog && !displayCreateDialog\" selectionMode=\"single\" [responsive]=\"true\"\r\n        (onRowSelect)=\"onRowSelect($event)\">\r\n        <header>Groups</header>\r\n        <p-column field=\"name\" header=\"Group Name\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n        <p-column field=\"comments\" header=\"Comments\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n        <p-footer>\r\n            <div class=\"row\">\r\n                <div class=\"col-md-12\">\r\n                    <button type=\"button\" icon=\"fa fa-plus\" pButton label=\"Create\" *ngIf=\"authorizationService.isUserHasRole('UI_CREATE_GROUPS')\" (click)=\"createGroupClick()\"></button>\r\n                    <!--button type=\"button\" icon=\"fa fa-download\" pButton label=\"Import\"></button-->\r\n                </div>\r\n            </div>\r\n        </p-footer>\r\n    </p-dataTable>\r\n\r\n    <!-- <p-dialog [(visible)]=\"displayViewDialog\" [responsive]=\"true\" showEffect=\"fade\" [modal]=\"true\" *ngIf=\"groupSelected\" [width]=\"800\"> -->\r\n\r\n    <div class=\"panel panel-default\" *ngIf=\"displayViewDialog && groupSelected\">\r\n        <div class=\"panel-heading\">\r\n            <h3 class=\"panel-title\">Group Details</h3>\r\n        </div>\r\n        <br/>\r\n        <div class=\"panel-body\">\r\n            <div class=\"row\">\r\n                <div class=\"col-md-3\">\r\n                    <div class=\"form-group\" *ngIf=\"!updateGroup\">\r\n                        <label for=\"name\">Group Name</label>\r\n                        <input disabled type=\"text\" class=\"form-control\" id=\"groupName\" required [(ngModel)]=\"groupSelected.name\" name=\"groupNameView\">\r\n                    </div>\r\n                    <div class=\"form-group\" *ngIf=\"updateGroup\">\r\n                        <label for=\"name\">Group Name</label>\r\n                        <input type=\"text\" class=\"form-control\" id=\"groupName\" required [(ngModel)]=\"groupSelected.name\" name=\"groupNameView\" #groupNameView=\"ngModel\">\r\n                        <div [hidden]=\"groupNameView.valid || groupNameView.pristine\" class=\"alert alert-danger\">\r\n                            Group name is required\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n                <div class=\"col-md-3\">\r\n                    <div class=\"form-group\">\r\n                        <label for=\"contactCount\">Contact Count</label>\r\n                        <input disabled type=\"text\" class=\"form-control\" id=\"contactCount\" required [(ngModel)]=\"groupSelected.contactCount\" name=\"contactCountView\">\r\n                    </div>\r\n                </div>\r\n                <div class=\"col-md-6\">\r\n                    <div class=\"form-group\" *ngIf=\"!updateGroup\">\r\n                        <label for=\"comments\">Comments</label>\r\n                        <input disabled type=\"text\" class=\"form-control\" id=\"comments\" [(ngModel)]=\"groupSelected.comments\" name=\"commentsView\">\r\n                    </div>\r\n                    <div class=\"form-group\" *ngIf=\"updateGroup\">\r\n                        <label for=\"comments\">Comments</label>\r\n                        <input type=\"text\" class=\"form-control\" id=\"comments\" [(ngModel)]=\"groupSelected.comments\" name=\"commentsView\">\r\n                    </div>\r\n                </div>\r\n            </div>\r\n            <div class=\"row\">\r\n                <div class=\"col-md-12\">\r\n                    <div class=\"col-md-5\"></div>\r\n                    <button *ngIf=\"!updateGroup && authorizationService.isUserHasRole('UI_UPDATE_GROUPS')\" type=\"button\" pButton icon=\"fa-pencil\" (click)=\"updateGroupClick()\" label=\"Edit\" ></button>\r\n                    <button *ngIf=\"!updateGroup && authorizationService.isUserHasRole('UI_UPDATE_GROUPS')\" [disabled]=\"groupSelected.contactCount > 0\" type=\"submit\" pButton icon=\"fa-trash\" (click)=\"deleteSelectedGroup()\"\r\n                        label=\"Delete\"></button>\r\n                    <button *ngIf=\"!updateGroup\" type=\"button\" pButton icon=\"fa-close\" (click)=\"viewDialogCancelClick()\" label=\"Cancel\"></button>\r\n                    <button *ngIf=\"updateGroup && authorizationService.isUserHasRole('UI_UPDATE_GROUPS')\" type=\"submit\" pButton icon=\"fa-check\" (click)=\"updateGroupSubmit()\" label=\"Submit\" [disabled]=\"!groupForm.form.valid\" ></button>\r\n                    <button *ngIf=\"updateGroup\" type=\"button\" pButton icon=\"fa-close\" (click)=\"dialogUpdateCancelClick()\" label=\"Cancel\"></button>\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </div>\r\n\r\n    <!-- </p-dialog> -->\r\n\r\n    <!-- <p-dialog [(visible)]=\"displayCreateDialog\" [responsive]=\"true\" showEffect=\"fade\" [modal]=\"true\" [width]=\"800\" *ngIf=\"groupNew\"> -->\r\n\r\n    <div class=\"panel panel-default\" *ngIf=\"displayCreateDialog && groupNew\">\r\n        <div class=\"panel-heading\">\r\n            <h3 class=\"panel-title\">Create Group</h3>\r\n        </div>\r\n        <br/>\r\n        <div class=\"panel-body\">\r\n            <div class=\"row\">\r\n                <div class=\"col-md-4\">\r\n                    <div class=\"form-group\">\r\n                        <label for=\"name\">Group Name</label>\r\n                        <input type=\"text\" class=\"form-control\" id=\"groupName\" required [(ngModel)]=\"groupNew.name\" name=\"groupNameCreate\" #groupNameCreate=\"ngModel\">\r\n                        <div [hidden]=\"groupNameCreate.valid || groupNameCreate.pristine\" class=\"alert alert-danger\">\r\n                            Group name is required\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n                <div class=\"col-md-8\">\r\n                    <div class=\"form-group\">\r\n                        <label for=\"comments\">Comments</label>\r\n                        <input type=\"text\" class=\"form-control\" id=\"comments\" [(ngModel)]=\"groupNew.comments\" name=\"commentsCreate\">\r\n                    </div>\r\n                </div>\r\n            </div>\r\n            <div class=\"row\">\r\n                <div class=\"col-md-12\">\r\n                    <div class=\"col-md-5\"></div>\r\n                    <button type=\"submit\" pButton icon=\"fa-check\" (click)=\"createGroupSubmit()\" label=\"Create\" *ngIf=\"authorizationService.isUserHasRole('UI_CREATE_GROUPS')\" [disabled]=\"!groupForm.form.valid\"></button>\r\n                    <button type=\"button\" pButton icon=\"fa-close\" (click)=\"createDialogCancelClick()\" label=\"Cancel\"></button>\r\n                </div>\r\n            </div>\r\n            <br/>\r\n        </div>\r\n    </div>\r\n\r\n    <!--  </p-dialog> -->\r\n\r\n</form>\r\n</div>"

/***/ }),

/***/ "../../../../../src/app/email/group/group.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__group_service__ = __webpack_require__("../../../../../src/app/email/group/group.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__group__ = __webpack_require__("../../../../../src/app/email/group/group.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__shared_common_service__ = __webpack_require__("../../../../../src/app/email/shared/common.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__core_authorization_service__ = __webpack_require__("../../../../../src/app/core/authorization.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__core_global_service__ = __webpack_require__("../../../../../src/app/core/global.service.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return GroupComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var GroupComponent = (function () {
    function GroupComponent(groupService, commonService, authorizationService, globalService) {
        this.groupService = groupService;
        this.commonService = commonService;
        this.authorizationService = authorizationService;
        this.globalService = globalService;
        this.msgs = [];
        this.active = true;
        this.username = globalService.loggedInUser.loggedInUserName;
    }
    GroupComponent.prototype.onRowSelect = function (event) {
        var _this = this;
        this.groupSelected = event.data;
        this.groupService.getGroupById(this.groupSelected.id)
            .subscribe(function (groupFromDB) {
            _this.groupSelected = groupFromDB;
            _this.displayViewDialog = true;
            _this.updateGroup = false;
        });
    };
    GroupComponent.prototype.viewDialogCancelClick = function () {
        this.displayViewDialog = false;
    };
    GroupComponent.prototype.createGroupClick = function () {
        var _this = this;
        this.groupNew = new __WEBPACK_IMPORTED_MODULE_2__group__["a" /* Group */]();
        this.displayCreateDialog = true;
        this.active = false;
        setTimeout(function () { return _this.active = true; }, 0);
    };
    GroupComponent.prototype.createDialogCancelClick = function () {
        this.displayCreateDialog = false;
    };
    GroupComponent.prototype.createGroupSubmit = function () {
        var _this = this;
        this.msgs = [];
        this.groupService.createGroup(this.groupNew)
            .subscribe(function () {
            //this.commonService.searchGroupsByCriteria();
            _this.commonService.resetGroupsBySearchCriteria();
            _this.displayCreateDialog = false;
            _this.commonService.getAllGroups(_this.globalService.loggedInUser.loggedInUserName);
            _this.commonService.contacts = [];
            _this.msgs.push({ severity: "info", summary: "Group created successfully.", detail: "" });
        }, function (error) {
            _this.msgs.push({ severity: "error", summary: "Group creation failed.", detail: error });
        });
    };
    GroupComponent.prototype.updateGroupClick = function () {
        this.updateGroup = true;
        this.groupUpdate = this.groupSelected;
        this.groupSelected = this.cloneGroup(this.groupUpdate);
    };
    GroupComponent.prototype.deleteSelectedGroup = function () {
        var _this = this;
        this.msgs = [];
        this.groupService.deleteGroup(this.groupSelected.id)
            .subscribe(function () {
            //this.commonService.searchGroupsByCriteria();
            _this.commonService.resetGroupsBySearchCriteria();
            _this.msgs.push({ severity: "info", summary: "Group deleted successfully.", detail: "" });
            _this.displayViewDialog = false;
            _this.commonService.contacts = [];
        }, function (error) {
            _this.msgs.push({ severity: "error", summary: "Group deletion failed.", detail: error });
        });
    };
    GroupComponent.prototype.updateGroupSubmit = function () {
        var _this = this;
        this.msgs = [];
        this.groupService.updateGroup(this.groupSelected)
            .subscribe(function (groupUpdated) {
            _this.groupSelected = groupUpdated;
            //this.commonService.searchGroupsByCriteria();
            _this.commonService.resetGroupsBySearchCriteria();
            _this.displayViewDialog = true;
            _this.updateGroup = false;
            _this.commonService.contacts = [];
            _this.msgs.push({ severity: "info", summary: "Group updated successfully.", detail: "" });
        }, function (error) {
            _this.msgs.push({ severity: "error", summary: "Group updation failed.", detail: error });
        });
    };
    GroupComponent.prototype.dialogUpdateCancelClick = function () {
        this.updateGroup = false;
        this.groupSelected = this.groupUpdate;
    };
    GroupComponent.prototype.cloneGroup = function (gro) {
        var group = new __WEBPACK_IMPORTED_MODULE_2__group__["a" /* Group */]();
        for (var prop in gro) {
            group[prop] = gro[prop];
        }
        return group;
    };
    return GroupComponent;
}());
GroupComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        template: __webpack_require__("../../../../../src/app/email/group/group.component.html")
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__group_service__["a" /* GroupService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__group_service__["a" /* GroupService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_3__shared_common_service__["a" /* CommonService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__shared_common_service__["a" /* CommonService */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_4__core_authorization_service__["a" /* AuthorizationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__core_authorization_service__["a" /* AuthorizationService */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_5__core_global_service__["a" /* GlobalService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_5__core_global_service__["a" /* GlobalService */]) === "function" && _d || Object])
], GroupComponent);

var _a, _b, _c, _d;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/group.component.js.map

/***/ }),

/***/ "../../../../../src/app/email/group/group.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__("../../../http/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__ = __webpack_require__("../../../../rxjs/Observable.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch__ = __webpack_require__("../../../../rxjs/add/operator/catch.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_catch__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map__ = __webpack_require__("../../../../rxjs/add/operator/map.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return GroupService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var GroupService = (function () {
    function GroupService(http) {
        this.http = http;
        this.groupUrl = "groups";
    }
    GroupService.prototype.getAllGroups = function (username) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* RequestOptions */]({ headers: headers });
        var urlSearchParams = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["e" /* URLSearchParams */]();
        urlSearchParams.append('username', username);
        var body = urlSearchParams.toString();
        return this.http.post(this.groupUrl + '/searchAll', body, options)
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    GroupService.prototype.getGroupById = function (id) {
        return this.http.get(this.groupUrl + "/" + id)
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    GroupService.prototype.getAllGroupsBySearchCriteria = function (groupSearchCriteria, username) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        headers.append('Access-Control-Allow-Origin', '*');
        groupSearchCriteria.username = username;
        return this.http.post(this.groupUrl + "/searchCriteria", JSON.stringify(groupSearchCriteria), { headers: headers })
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    GroupService.prototype.createGroup = function (group) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.post(this.groupUrl, JSON.stringify(group), { headers: headers })
            .map(function (res) { return; })
            .catch(this.handleError);
    };
    GroupService.prototype.updateGroup = function (group) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.put(this.groupUrl + "/" + group.id, JSON.stringify(group), { headers: headers })
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    GroupService.prototype.deleteGroup = function (objectId) {
        return this.http.delete(this.groupUrl + "/" + objectId)
            .map(function (res) { return; })
            .catch(this.handleError);
    };
    GroupService.prototype.handleError = function (error) {
        var errMsg = (error.message) ? error.message :
            error.status ? error.status + " - " + error.statusText : 'Server error';
        console.error(errMsg);
        return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].throw(errMsg);
    };
    return GroupService;
}());
GroupService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */]) === "function" && _a || Object])
], GroupService);

var _a;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/group.service.js.map

/***/ }),

/***/ "../../../../../src/app/email/group/group.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__core_model_base_entity__ = __webpack_require__("../../../../../src/app/core/model/base.entity.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Group; });
var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var Group = (function (_super) {
    __extends(Group, _super);
    function Group() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    return Group;
}(__WEBPACK_IMPORTED_MODULE_0__core_model_base_entity__["a" /* BaseEntity */]));

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/group.js.map

/***/ }),

/***/ "../../../../../src/app/email/group/group_search_criteria.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return GroupSearchCriteria; });
var GroupSearchCriteria = (function () {
    function GroupSearchCriteria() {
    }
    return GroupSearchCriteria;
}());

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/group_search_criteria.js.map

/***/ }),

/***/ "../../../../../src/app/email/server/emailServerPropertyValueTypeConstant.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return EmailServerPropertyValueTypeConstant; });
var EmailServerPropertyValueTypeConstant;
(function (EmailServerPropertyValueTypeConstant) {
    EmailServerPropertyValueTypeConstant[EmailServerPropertyValueTypeConstant["STRING"] = "STRING"] = "STRING";
    EmailServerPropertyValueTypeConstant[EmailServerPropertyValueTypeConstant["NUMBER"] = "NUMBER"] = "NUMBER";
    EmailServerPropertyValueTypeConstant[EmailServerPropertyValueTypeConstant["BOOLEAN"] = "BOOLEAN"] = "BOOLEAN";
})(EmailServerPropertyValueTypeConstant || (EmailServerPropertyValueTypeConstant = {}));
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/emailServerPropertyValueTypeConstant.js.map

/***/ }),

/***/ "../../../../../src/app/email/server/emailserver.component.html":
/***/ (function(module, exports) {

module.exports = "<p-growl [value]=\"msgs\" sticky=\"sticky\"></p-growl>\r\n\r\n<!--div class=\"main-panel\" style=\"min-height:110vh;\"-->\r\n<form #contactForm=\"ngForm\" *ngIf=\"active\">\r\n\r\n<div class=\"panel panel-default\" *ngIf=\"!createEmailServer && !viewEmailServer\">\r\n\t\t<div class=\"panel-heading\">\r\n\t\t\t<h3 class=\"panel-title\">Email Server Configurations</h3>\r\n\t\t</div>\r\n\t\t<div class=\"panel-body\">\r\n\t\t\t<p-dataTable [value]=\"emailServers\" *ngIf=\"!createEmailServer && !viewEmailServer\" [rows]=\"50\" [paginator]=\"true\" [pageLinks]=\"3\"\r\n\t\t\t\t[rowsPerPageOptions]=\"[10,20,50]\" selectionMode=\"single\" [responsive]=\"true\" (onRowSelect)=\"onRowSelect($event)\">\r\n\t\t\t\t<header>Email Servers</header>\r\n\t\t\t\t<p-column field=\"name\" header=\"Name\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n\t\t\t\t<p-column field=\"protocol\" header=\"Protocol\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n\t\t\t\t<p-column field=\"host\" header=\"Host\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n\t\t\t\t<p-column field=\"port\" header=\"Port\" [sortable]=\"true\" [filter]=\"true\" [colspan]=\"2\"></p-column>\r\n\t\t\t\t<p-footer>\r\n\t\t\t\t\t<div class=\"row\">\r\n\t\t\t\t\t\t<div class=\"col-md-12\">\r\n\t\t\t\t\t\t\t<button type=\"button\" pButton icon=\"fa fa-plus\" pButton label=\"Create\" *ngIf=\"authorizationService.isUserHasRole('UI_ACCESS_SERVERS')\" (click)=\"createEmailServerClick()\"></button>\r\n\t\t\t\t\t\t\t<!--button type=\"button\" pButton icon=\"fa fa-download\" label=\"Import\"></button-->\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t</div>\r\n\t\t\t\t</p-footer>\r\n\t\t\t</p-dataTable>\r\n\t\t</div>\r\n\t</div>\t\r\n\r\n\t<div class=\"panel panel-default\" *ngIf=\"viewEmailServer && emailServerSelected\">\r\n\t\t<div class=\"panel-heading\">\r\n\t\t\t<h3 class=\"panel-title\">Email Server</h3>\r\n\t\t</div>\r\n\t\t<div class=\"panel-body\">\r\n\t\t\t<p-tabView>\r\n\t\t\t\t<p-tabPanel header=\"Email Server Details\">\r\n\t\t\t\t\t<div class=\"row\">\r\n\t\t\t\t\t\t<div class=\"col-md-4\">\r\n\t\t\t\t\t\t\t<div class=\"form-group\" *ngIf=\"!updateEmailServer\">\r\n\t\t\t\t\t\t\t\t<label for=\"emailServerName\">Email Server Name*</label>\r\n\t\t\t\t\t\t\t\t<input disabled type=\"text\" class=\"form-control\" id=\"emailServerName\" [(ngModel)]=\"emailServerSelected.name\" name=\"emailServerNameView\">\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t<div class=\"form-group\" *ngIf=\"updateEmailServer\">\r\n\t\t\t\t\t\t\t\t<label for=\"emailServerName\">Email Server Name*</label>\r\n\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"emailServerName\" required [(ngModel)]=\"emailServerSelected.name\" name=\"emailServerNameView\"\r\n\t\t\t\t\t\t\t\t\t#emailServerNameView=\"ngModel\" placeholder=\"Required\">\r\n\t\t\t\t\t\t\t\t<div [hidden]=\"emailServerNameView.valid || emailServerNameView.pristine\" class=\"alert alert-danger\">\r\n\t\t\t\t\t\t\t\t\tEmail server name is required\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t<div class=\"col-md-4\">\r\n\t\t\t\t\t\t\t<div class=\"form-group\" *ngIf=\"!updateEmailServer\">\r\n\t\t\t\t\t\t\t\t<label for=\"protocol\">Protocol*</label>\r\n\t\t\t\t\t\t\t\t<input disabled type=\"text\" class=\"form-control\" id=\"protocol\" [(ngModel)]=\"emailServerSelected.protocol\" name=\"protocolView\">\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t<div class=\"form-group\" *ngIf=\"updateEmailServer\">\r\n\t\t\t\t\t\t\t\t<label for=\"protocol\">Protocol*</label>\r\n\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"protocol\" required [(ngModel)]=\"emailServerSelected.protocol\" name=\"protocolView\"\r\n\t\t\t\t\t\t\t\t\t#protocolView=\"ngModel\" placeholder=\"Required\">\r\n\t\t\t\t\t\t\t\t<div [hidden]=\"protocolView.valid || protocolView.pristine\" class=\"alert alert-danger\">\r\n\t\t\t\t\t\t\t\t\tProtocol is required\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t<div class=\"col-md-4\">\r\n\t\t\t\t\t\t\t<div class=\"form-group\" *ngIf=\"!updateEmailServer\">\r\n\t\t\t\t\t\t\t\t<label for=\"host\">Host*</label>\r\n\t\t\t\t\t\t\t\t<input disabled type=\"text\" class=\"form-control\" id=\"host\" [(ngModel)]=\"emailServerSelected.host\" name=\"hostView\">\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t<div class=\"form-group\" *ngIf=\"updateEmailServer\">\r\n\t\t\t\t\t\t\t\t<label for=\"host\">Host*</label>\r\n\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"host\" required [(ngModel)]=\"emailServerSelected.host\" name=\"hostView\" #hostView=\"ngModel\"\r\n\t\t\t\t\t\t\t\t\tplaceholder=\"Required\">\r\n\t\t\t\t\t\t\t\t<div [hidden]=\"hostView.valid || hostView.pristine\" class=\"alert alert-danger\">\r\n\t\t\t\t\t\t\t\t\tHost is required\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t</div>\r\n\t\t\t\t\t<div class=\"row\">\r\n\t\t\t\t\t\t<div class=\"col-md-4\">\r\n\t\t\t\t\t\t\t<div class=\"form-group\" *ngIf=\"!updateEmailServer\">\r\n\t\t\t\t\t\t\t\t<label for=\"port\">Port*</label>\r\n\t\t\t\t\t\t\t\t<input disabled type=\"text\" class=\"form-control\" id=\"port\" [(ngModel)]=\"emailServerSelected.port\" name=\"portView\">\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t<div class=\"form-group\" *ngIf=\"updateEmailServer\">\r\n\t\t\t\t\t\t\t\t<label for=\"port\">Port*</label>\r\n\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"port\" required [(ngModel)]=\"emailServerSelected.port\" name=\"portView\" #portView=\"ngModel\"\r\n\t\t\t\t\t\t\t\t\tplaceholder=\"Required\">\r\n\t\t\t\t\t\t\t\t<div [hidden]=\"portView.valid || portView.pristine\" class=\"alert alert-danger\">\r\n\t\t\t\t\t\t\t\t\tPort is required\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t<div class=\"col-md-4\">\r\n\t\t\t\t\t\t\t<div class=\"form-group\" *ngIf=\"!updateEmailServer\">\r\n\t\t\t\t\t\t\t\t<label for=\"mailsPerSession\">Mails Per Session*</label>\r\n\t\t\t\t\t\t\t\t<input disabled type=\"text\" class=\"form-control\" id=\"mailsPerSession\" [(ngModel)]=\"emailServerSelected.mailsPerSession\" name=\"mailsPerSessionView\">\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t<div class=\"form-group\" *ngIf=\"updateEmailServer\">\r\n\t\t\t\t\t\t\t\t<label for=\"mailsPerSession\">Mails Per Session*</label>\r\n\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"mailsPerSession\" required [(ngModel)]=\"emailServerSelected.mailsPerSession\" name=\"mailsPerSessionView\"\r\n\t\t\t\t\t\t\t\t\t#mailsPerSessionView=\"ngModel\" placeholder=\"Required\">\r\n\t\t\t\t\t\t\t\t<div [hidden]=\"mailsPerSessionView.valid || mailsPerSessionView.pristine\" class=\"alert alert-danger\">\r\n\t\t\t\t\t\t\t\t\tMails Per Session is required\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t<div class=\"col-md-4\">\r\n\t\t\t\t\t\t\t<div class=\"form-group\" *ngIf=\"!updateEmailServer\">\r\n\t\t\t\t\t\t\t\t<label for=\"fromAddress\">From Address*</label>\r\n\t\t\t\t\t\t\t\t<input disabled type=\"text\" class=\"form-control\" id=\"fromAddress\" [(ngModel)]=\"emailServerSelected.fromAddress\" name=\"fromAddressView\">\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t<div class=\"form-group\" *ngIf=\"updateEmailServer\">\r\n\t\t\t\t\t\t\t\t<label for=\"fromAddress\">From Address*</label>\r\n\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"fromAddress\" required [(ngModel)]=\"emailServerSelected.fromAddress\" name=\"fromAddressView\"\r\n\t\t\t\t\t\t\t\t\t#fromAddressView=\"ngModel\" placeholder=\"Required\">\r\n\t\t\t\t\t\t\t\t<div [hidden]=\"fromAddressView.valid || fromAddressView.pristine\" class=\"alert alert-danger\">\r\n\t\t\t\t\t\t\t\t\tFrom Address is required\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t</div>\r\n\t\t\t\t\t<div class=\"row\">\r\n\t\t\t\t\t\t<div class=\"col-md-4\">\r\n\t\t\t\t\t\t\t<div class=\"form-group\" *ngIf=\"!updateEmailServer\">\r\n\t\t\t\t\t\t\t\t<label for=\"emailUsername\">Email User name*</label>\r\n\t\t\t\t\t\t\t\t<input disabled type=\"text\" class=\"form-control\" id=\"emailUsername\" [(ngModel)]=\"emailServerSelected.emailUsername\" name=\"emailUsernameView\">\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t<div class=\"form-group\" *ngIf=\"updateEmailServer\">\r\n\t\t\t\t\t\t\t\t<label for=\"emailUsername\">Email User name*</label>\r\n\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"emailUsername\" required [(ngModel)]=\"emailServerSelected.emailUsername\" name=\"emailUsernameView\"\r\n\t\t\t\t\t\t\t\t\t#emailUsernameView=\"ngModel\" placeholder=\"Required\">\r\n\t\t\t\t\t\t\t\t<div [hidden]=\"emailUsernameView.valid || emailUsernameView.pristine\" class=\"alert alert-danger\">\r\n\t\t\t\t\t\t\t\t\tEmail Username is required\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t<div class=\"col-md-4\">\r\n\t\t\t\t\t\t\t<div class=\"form-group\" *ngIf=\"!updateEmailServer\">\r\n\t\t\t\t\t\t\t\t<label for=\"emailPassword\">Email Password*</label>\r\n\t\t\t\t\t\t\t\t<input disabled type=\"password\" class=\"form-control\" id=\"emailPassword\" [(ngModel)]=\"emailServerSelected.emailPassword\" name=\"emailPasswordView\">\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t<div class=\"form-group\" *ngIf=\"updateEmailServer\">\r\n\t\t\t\t\t\t\t\t<label for=\"emailPassword\">Email Password*</label>\r\n\t\t\t\t\t\t\t\t<input type=\"password\" class=\"form-control\" id=\"emailPassword\" required [(ngModel)]=\"emailServerSelected.emailPassword\" name=\"emailPasswordView\"\r\n\t\t\t\t\t\t\t\t\t#emailPasswordView=\"ngModel\" placeholder=\"Required\">\r\n\t\t\t\t\t\t\t\t<div [hidden]=\"emailPasswordView.valid || emailPasswordView.pristine\" class=\"alert alert-danger\">\r\n\t\t\t\t\t\t\t\t\tMails Password is required\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t</div>\r\n\t\t\t\t\t<div class=\"row\">\r\n\t\t\t\t\t\t<div class=\"col-md-12\">\r\n\t\t\t\t\t\t\t<hr/>\r\n\t\t\t\t\t\t\t<hr/>\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t</div>\r\n\t\t\t\t\t<div class=\"row\">\r\n\t\t\t\t\t\t<div class=\"col-md-12\">\r\n\t\t\t\t\t\t\t<div class=\"col-md-5\"></div>\r\n\t\t\t\t\t\t\t<button *ngIf=\"!updateEmailServer && authorizationService.isUserHasRole('UI_UPDATE_SERVERS')\" type=\"button\" pButton icon=\"fa-pencil\" (click)=\"updateEmailServerClick()\" label=\"Edit\"></button>\r\n\t\t\t\t\t\t\t<button *ngIf=\"!updateEmailServer && authorizationService.isUserHasRole('UI_DELETE_SERVERS')\" type=\"submit\" pButton icon=\"fa-trash\" (click)=\"deleteEmailServerCancleClick()\" label=\"Delete\"></button>\r\n\t\t\t\t\t\t\t<button *ngIf=\"!updateEmailServer\" type=\"button\" pButton icon=\"fa-close\" (click)=\"viewEmailServerCancleClick()\" label=\"Cancel\"></button>\r\n\t\t\t\t\t\t\t<button *ngIf=\"updateEmailServer && authorizationService.isUserHasRole('UI_UPDATE_SERVERS')\" type=\"submit\" pButton icon=\"fa-check\" (click)=\"updateEmailServerSubmit()\" label=\"Submit\"\r\n\t\t\t\t\t\t\t\t[disabled]=\"!contactForm.form.valid\"></button>\r\n\t\t\t\t\t\t\t<button *ngIf=\"updateEmailServer\" type=\"button\" pButton icon=\"fa-close\" (click)=\"updateEmailServerCancel()\" label=\"Cancel\"></button>\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t</div>\r\n\t\t\t\t</p-tabPanel>\r\n\t\t\t\t<p-tabPanel header=\"Email Server Details\">\r\n\t\t\t\t\t<form #emailServerPropertiesUpdateForm=\"ngForm\" *ngIf=\"active2\">\r\n\t\t\t\t\t\t<div class=\"row\" *ngIf=\"updateEmailServer\">\r\n\t\t\t\t\t\t\t<div class=\"col-md-4\">\r\n\t\t\t\t\t\t\t\t<div class=\"form-group\">\r\n\t\t\t\t\t\t\t\t\t<label for=\"emailPassword\">Property Name*</label>\r\n\t\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"propertyName\" required [(ngModel)]=\"emailServerPropertyNew.propertyName\" name=\"propertyNameView\"\r\n\t\t\t\t\t\t\t\t\t\t#propertyNameView=\"ngModel\" placeholder=\"Required\">\r\n\t\t\t\t\t\t\t\t\t<div [hidden]=\"propertyNameView.valid || propertyNameView.pristine\" class=\"alert alert-danger\">\r\n\t\t\t\t\t\t\t\t\t\tProperty name is required\r\n\t\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t<div class=\"col-md-4\">\r\n\t\t\t\t\t\t\t\t<div class=\"form-group\">\r\n\t\t\t\t\t\t\t\t\t<label for=\"emailPassword\">Email Value*</label>\r\n\t\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"propertyValue\" required [(ngModel)]=\"emailServerPropertyNew.value\" name=\"propertyValueView\"\r\n\t\t\t\t\t\t\t\t\t\t#propertyValueView=\"ngModel\" placeholder=\"Required\">\r\n\t\t\t\t\t\t\t\t\t<div [hidden]=\"propertyValueView.valid || propertyValueView.pristine\" class=\"alert alert-danger\">\r\n\t\t\t\t\t\t\t\t\t\tProperty value is required\r\n\t\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t<div class=\"col-md-4\">\r\n\t\t\t\t\t\t\t\t<div class=\"form-group\">\r\n\t\t\t\t\t\t\t\t\t<label for=\"emailPassword\">Property Type*</label>\r\n\t\t\t\t\t\t\t\t\t<p-dropdown [style]=\"{'width':'300px'}\" id=\"propertyType\" placeholder=\"Select\" required [options]=\"emailServerPropertyTypes\"\r\n\t\t\t\t\t\t\t\t\t\t[(ngModel)]=\"emailServerPropertyNew.emailServerPropertyValueTypeConstant\" #propertyTypeView=\"ngModel\" name=\"propertyTypeView\"></p-dropdown>\r\n\t\t\t\t\t\t\t\t\t<div [hidden]=\"propertyTypeView.valid || propertyTypeView.pristine\" class=\"alert alert-danger\">\r\n\t\t\t\t\t\t\t\t\t\tProperty type is required\r\n\t\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t<div class=\"row\">\r\n\t\t\t\t\t\t\t\t<div class=\"col-md-12\">\r\n\t\t\t\t\t\t\t\t\t<button type=\"button\" pButton icon=\"fa-check\" (click)=\"createEmailServerProperty(emailServerPropertyNew)\" label=\"Add\"\r\n\t\t\t\t\t\t\t\t\t\t[disabled]=\"!emailServerPropertiesUpdateForm.form.valid\" *ngIf=\"authorizationService.isUserHasRole('UI_CREATE_SERVERS')\"></button>\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t<br/>\r\n\t\t\t\t\t\t<p-dataTable [value]=\"emailServerPropertiesForServer\" *ngIf=\"emailServerSelected && emailServerPropertiesForServer && emailServerPropertiesForServer.length > 0\"\r\n\t\t\t\t\t\t\t[rows]=\"50\">\r\n\t\t\t\t\t\t\t<header>Email Servers</header>\r\n\t\t\t\t\t\t\t<p-column field=\"propertyName\" header=\"Name\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n\t\t\t\t\t\t\t<p-column field=\"value\" header=\"Value\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n\t\t\t\t\t\t\t<p-column field=\"emailServerPropertyValueTypeConstant\" header=\"Type\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n\t\t\t\t\t\t\t<p-column styleClass=\"col-button\">\r\n\t\t\t\t\t\t\t\t<template let-emailServerProperties=\"rowData\" pTemplate type=\"body\">\r\n\t\t\t\t\t\t\t\t\t<button type=\"button\" [disabled]=\"!updateEmailServer\" pButton icon=\"fa-trash\" (click)=\"deleteEmailServerProperty(emailServerProperties)\"\r\n\t\t\t\t\t\t\t\t\t\tlabel=\"Delete\" *ngIf=\"authorizationService.isUserHasRole('UI_DELETE_SERVERS')\"></button></template>\r\n\t\t\t\t\t\t\t</p-column>\r\n\t\t\t\t\t\t</p-dataTable>\r\n\t\t\t\t\t</form>\r\n\t\t\t\t</p-tabPanel>\r\n\t\t\t</p-tabView>\r\n\t\t</div>\r\n\t\t<br/>\r\n\t</div>\r\n\r\n\t<div class=\"panel panel-default\" *ngIf=\"createEmailServer\">\r\n\t\t<div class=\"panel-heading\">\r\n\t\t\t<h3 class=\"panel-title\">Create Email Server</h3>\r\n\t\t</div>\r\n\t\t<div class=\"panel-body\">\r\n\t\t\t<p-tabView>\r\n\t\t\t\t<p-tabPanel header=\"Email Server Details\">\r\n\t\t\t\t\t<div class=\"row\">\r\n\t\t\t\t\t\t<div class=\"col-md-4\">\r\n\t\t\t\t\t\t\t<div class=\"form-group\">\r\n\t\t\t\t\t\t\t\t<label for=\"emailServerName\">Email Server Name*</label>\r\n\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"emailServerName\" required [(ngModel)]=\"emailServerNew.name\" name=\"emailServerNameCreate\"\r\n\t\t\t\t\t\t\t\t\t#emailServerNameCreate=\"ngModel\" placeholder=\"Required\">\r\n\t\t\t\t\t\t\t\t<div [hidden]=\"emailServerNameCreate.valid || emailServerNameCreate.pristine\" class=\"alert alert-danger\">\r\n\t\t\t\t\t\t\t\t\tEmail server name is required\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t<div class=\"col-md-4\">\r\n\t\t\t\t\t\t\t<div class=\"form-group\">\r\n\t\t\t\t\t\t\t\t<label for=\"protocol\">Protocol*</label>\r\n\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"protocol\" required [(ngModel)]=\"emailServerNew.protocol\" name=\"protocolCreate\"\r\n\t\t\t\t\t\t\t\t\t#protocolCreate=\"ngModel\" placeholder=\"Required\">\r\n\t\t\t\t\t\t\t\t<div [hidden]=\"protocolCreate.valid || protocolCreate.pristine\" class=\"alert alert-danger\">\r\n\t\t\t\t\t\t\t\t\tProtocol is required\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t<div class=\"col-md-4\">\r\n\t\t\t\t\t\t\t<div class=\"form-group\">\r\n\t\t\t\t\t\t\t\t<label for=\"host\">Host*</label>\r\n\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"host\" required [(ngModel)]=\"emailServerNew.host\" name=\"hostCreate\" #hostCreate=\"ngModel\"\r\n\t\t\t\t\t\t\t\t\tplaceholder=\"Required\">\r\n\t\t\t\t\t\t\t\t<div [hidden]=\"hostCreate.valid || hostCreate.pristine\" class=\"alert alert-danger\">\r\n\t\t\t\t\t\t\t\t\tHost is required\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t</div>\r\n\t\t\t\t\t<div class=\"row\">\r\n\t\t\t\t\t\t<div class=\"col-md-4\">\r\n\t\t\t\t\t\t\t<div class=\"form-group\">\r\n\t\t\t\t\t\t\t\t<label for=\"port\">Port*</label>\r\n\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"port\" required [(ngModel)]=\"emailServerNew.port\" name=\"portCreate\" #portCreate=\"ngModel\"\r\n\t\t\t\t\t\t\t\t\tplaceholder=\"Required\">\r\n\t\t\t\t\t\t\t\t<div [hidden]=\"portCreate.valid || portCreate.pristine\" class=\"alert alert-danger\">\r\n\t\t\t\t\t\t\t\t\tPort is required\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t<div class=\"col-md-4\">\r\n\t\t\t\t\t\t\t<div class=\"form-group\">\r\n\t\t\t\t\t\t\t\t<label for=\"mailsPerSession\">Mails Per Session*</label>\r\n\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"mailsPerSession\" required [(ngModel)]=\"emailServerNew.mailsPerSession\" name=\"mailsPerSessionCreate\"\r\n\t\t\t\t\t\t\t\t\t#mailsPerSessionCreate=\"ngModel\" placeholder=\"Required\">\r\n\t\t\t\t\t\t\t\t<div [hidden]=\"mailsPerSessionCreate.valid || mailsPerSessionCreate.pristine\" class=\"alert alert-danger\">\r\n\t\t\t\t\t\t\t\t\tMails Per Session is required\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t<div class=\"col-md-4\">\r\n\t\t\t\t\t\t\t<div class=\"form-group\">\r\n\t\t\t\t\t\t\t\t<label for=\"fromAddress\">From Address*</label>\r\n\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"fromAddress\" required [(ngModel)]=\"emailServerNew.fromAddress\" name=\"fromAddressCreate\"\r\n\t\t\t\t\t\t\t\t\t#fromAddressCreate=\"ngModel\" placeholder=\"Required\">\r\n\t\t\t\t\t\t\t\t<div [hidden]=\"fromAddressCreate.valid || fromAddressCreate.pristine\" class=\"alert alert-danger\">\r\n\t\t\t\t\t\t\t\t\tFrom Address is required\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t</div>\r\n\t\t\t\t\t<div class=\"row\">\r\n\t\t\t\t\t\t<div class=\"col-md-4\">\r\n\t\t\t\t\t\t\t<div class=\"form-group\">\r\n\t\t\t\t\t\t\t\t<label for=\"emailUsername\">Email User name*</label>\r\n\t\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"emailUsername\" required [(ngModel)]=\"emailServerNew.emailUsername\" name=\"emailUsernameCreate\"\r\n\t\t\t\t\t\t\t\t\t#emailUsernameCreate=\"ngModel\" placeholder=\"Required\">\r\n\t\t\t\t\t\t\t\t<div [hidden]=\"emailUsernameCreate.valid || emailUsernameCreate.pristine\" class=\"alert alert-danger\">\r\n\t\t\t\t\t\t\t\t\tEmail Username is required\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t<div class=\"col-md-4\">\r\n\t\t\t\t\t\t\t<div class=\"form-group\">\r\n\t\t\t\t\t\t\t\t<label for=\"emailPassword\">Email Password*</label>\r\n\t\t\t\t\t\t\t\t<input type=\"password\" class=\"form-control\" id=\"emailPassword\" required [(ngModel)]=\"emailServerNew.emailPassword\" name=\"emailPasswordCreate\"\r\n\t\t\t\t\t\t\t\t\t#emailPasswordCreate=\"ngModel\" placeholder=\"Required\">\r\n\t\t\t\t\t\t\t\t<div [hidden]=\"emailPasswordCreate.valid || emailPasswordCreate.pristine\" class=\"alert alert-danger\">\r\n\t\t\t\t\t\t\t\t\tMails Password is required\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t</div>\r\n\t\t\t\t</p-tabPanel>\r\n\t\t\t</p-tabView>\r\n\t\t\t<div class=\"row\">\r\n\t\t\t\t<div class=\"col-md-12\">\r\n\t\t\t\t\t<hr/>\r\n\t\t\t\t\t<hr/>\r\n\t\t\t\t</div>\r\n\t\t\t</div>\r\n\t\t\t<div class=\"col-md-12\">\r\n\t\t\t\t<div class=\"col-md-5\"></div>\r\n\t\t\t\t<button type=\"submit\" pButton icon=\"fa-check\" (click)=\"createEmailServerSubmit()\" label=\"Create\" [disabled]=\"!contactForm.form.valid\" *ngIf=\"authorizationService.isUserHasRole('UI_CREATE_SERVERS')\"></button>\r\n\t\t\t\t<button type=\"button\" pButton icon=\"fa-close\" (click)=\"createEmailServerCancleClick()\" label=\"Cancel\"></button>\r\n\t\t\t</div>\r\n\t\t</div>\r\n\t</div>\r\n</form>\r\n\r\n<!--/div-->"

/***/ }),

/***/ "../../../../../src/app/email/server/emailserver.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__emailserver__ = __webpack_require__("../../../../../src/app/email/server/emailserver.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__emailserver_service__ = __webpack_require__("../../../../../src/app/email/server/emailserver.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__emailserver_properties__ = __webpack_require__("../../../../../src/app/email/server/emailserver.properties.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__emailServerPropertyValueTypeConstant__ = __webpack_require__("../../../../../src/app/email/server/emailServerPropertyValueTypeConstant.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__emailserverproperties_service__ = __webpack_require__("../../../../../src/app/email/server/emailserverproperties.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__core_authorization_service__ = __webpack_require__("../../../../../src/app/core/authorization.service.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return EmailServerComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};







var EmailServerComponent = (function () {
    function EmailServerComponent(emailServerService, emailServerPropertiesService, authorizationService) {
        this.emailServerService = emailServerService;
        this.emailServerPropertiesService = emailServerPropertiesService;
        this.authorizationService = authorizationService;
        this.msgs = [];
        this.emailServers = [];
        this.emailServerPropertiesForServer = [];
        this.active = true;
        this.active2 = true;
        this.active3 = true;
    }
    EmailServerComponent.prototype.ngOnInit = function () {
        this.getAllEmailServers();
        this.loadEmailServerPropertyType();
    };
    EmailServerComponent.prototype.loadEmailServerPropertyType = function () {
        this.emailServerPropertyTypes = [];
        this.emailServerPropertyTypes.push({ label: 'Select Type', value: null });
        this.emailServerPropertyTypes.push({ label: 'String', value: __WEBPACK_IMPORTED_MODULE_4__emailServerPropertyValueTypeConstant__["a" /* EmailServerPropertyValueTypeConstant */].STRING });
        this.emailServerPropertyTypes.push({ label: 'Number', value: __WEBPACK_IMPORTED_MODULE_4__emailServerPropertyValueTypeConstant__["a" /* EmailServerPropertyValueTypeConstant */].NUMBER });
        this.emailServerPropertyTypes.push({ label: 'Boolean', value: __WEBPACK_IMPORTED_MODULE_4__emailServerPropertyValueTypeConstant__["a" /* EmailServerPropertyValueTypeConstant */].BOOLEAN });
    };
    EmailServerComponent.prototype.onRowSelect = function (event) {
        var _this = this;
        this.emailServerSelected = event.data;
        this.emailServerPropertyNew = new __WEBPACK_IMPORTED_MODULE_3__emailserver_properties__["a" /* EmailServerProperties */]();
        this.viewEmailServer = true;
        this.active = false;
        this.getAllEmailServerProperties();
        setTimeout(function () { return _this.active = true; }, 0);
    };
    EmailServerComponent.prototype.getAllEmailServers = function () {
        var _this = this;
        this.emailServerService.getAllEmailServers()
            .subscribe(function (emailServers) { _this.emailServers = emailServers; });
    };
    EmailServerComponent.prototype.createEmailServerClick = function () {
        var _this = this;
        this.emailServerNew = new __WEBPACK_IMPORTED_MODULE_1__emailserver__["a" /* EmailServer */]();
        this.createEmailServer = true;
        this.emailServerPropertyNew = new __WEBPACK_IMPORTED_MODULE_3__emailserver_properties__["a" /* EmailServerProperties */]();
        this.emailServerNew.emailServerProperties = [];
        this.active = false;
        setTimeout(function () { return _this.active = true; }, 0);
    };
    EmailServerComponent.prototype.getAllEmailServerProperties = function () {
        var _this = this;
        this.emailServerPropertiesService.getEmailServerPropertiesByEmailServerId(this.emailServerSelected.id)
            .subscribe(function (emailServers) { _this.emailServerPropertiesForServer = emailServers; }, function (error) {
            _this.msgs.push({ severity: "error", summary: "Error while retrieving Email Server Property for server.", detail: error });
        });
    };
    EmailServerComponent.prototype.createEmailServerProperty = function () {
        var _this = this;
        this.msgs = [];
        this.emailServerPropertiesService.createEmailServerProperty(this.emailServerPropertyNew, this.emailServerSelected.id)
            .subscribe(function () {
            _this.msgs.push({ severity: "info", summary: "Email Server Property created successfully.", detail: "" });
            _this.emailServerPropertyNew = new __WEBPACK_IMPORTED_MODULE_3__emailserver_properties__["a" /* EmailServerProperties */]();
            _this.getAllEmailServerProperties();
        }, function (error) {
            _this.msgs.push({ severity: "error", summary: "Email Server Property creation failed.", detail: error });
        });
    };
    EmailServerComponent.prototype.deleteEmailServerProperty = function (emailServerProperties) {
        var _this = this;
        this.msgs = [];
        this.emailServerPropertiesService.deleteEmailServerProperty(emailServerProperties.id)
            .subscribe(function () {
            _this.msgs.push({ severity: "info", summary: "Email Server Property deleted successfully.", detail: "" });
            _this.emailServerPropertyNew = new __WEBPACK_IMPORTED_MODULE_3__emailserver_properties__["a" /* EmailServerProperties */]();
            _this.getAllEmailServerProperties();
        }, function (error) {
            _this.msgs.push({ severity: "error", summary: "Email Server Property deletion failed.", detail: error });
        });
    };
    EmailServerComponent.prototype.createEmailServerSubmit = function () {
        var _this = this;
        this.msgs = [];
        this.emailServerService.createEmailServer(this.emailServerNew)
            .subscribe(function () {
            _this.msgs.push({ severity: "info", summary: "Email Server created successfully.", detail: "" });
            _this.createEmailServer = false;
            _this.getAllEmailServers();
        }, function (error) {
            _this.msgs.push({ severity: "error", summary: "Email Server creation failed.", detail: error });
        });
    };
    EmailServerComponent.prototype.updateEmailServerSubmit = function () {
        var _this = this;
        this.emailServerService.updateEmailServerSubmit(this.emailServerSelected)
            .subscribe(function (emailServer) {
            _this.emailServerSelected = emailServer;
            _this.viewEmailServer = true;
            _this.updateEmailServer = false;
            _this.msgs.push({ severity: "info", summary: "Email Server updated successfully.", detail: "" });
        }, function (error) {
            _this.msgs.push({ severity: "error", summary: "Email Server updation failed.", detail: error });
        });
    };
    EmailServerComponent.prototype.createEmailServerCancleClick = function () {
        this.createEmailServer = false;
        this.updateEmailServer = false;
    };
    EmailServerComponent.prototype.viewEmailServerCancleClick = function () {
        this.viewEmailServer = false;
        this.updateEmailServer = false;
    };
    EmailServerComponent.prototype.updateEmailServerClick = function () {
        this.updateEmailServer = true;
        this.viewEmailServer = true;
        this.emailServerUpdate = this.emailServerSelected;
        this.emailServerSelected = this.cloneContact(this.emailServerUpdate);
    };
    EmailServerComponent.prototype.updateEmailServerCancel = function () {
        this.updateEmailServer = false;
        this.viewEmailServer = true;
        this.emailServerSelected = this.emailServerUpdate;
    };
    EmailServerComponent.prototype.cloneContact = function (server) {
        var emailServer = new __WEBPACK_IMPORTED_MODULE_1__emailserver__["a" /* EmailServer */]();
        for (var prop in server) {
            emailServer[prop] = server[prop];
        }
        return emailServer;
    };
    EmailServerComponent.prototype.deleteEmailServerCancleClick = function () {
        var _this = this;
        this.msgs = [];
        this.emailServerService.deleteEmailServer(this.emailServerSelected.id)
            .subscribe(function () {
            _this.msgs.push({ severity: "info", summary: "Email Server deleted successfully.", detail: "" });
            _this.viewEmailServer = false;
            _this.getAllEmailServers();
        }, function (error) {
            _this.msgs.push({ severity: "error", summary: "Email Server deletion failed.", detail: error });
        });
    };
    return EmailServerComponent;
}());
EmailServerComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        template: __webpack_require__("../../../../../src/app/email/server/emailserver.component.html")
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_2__emailserver_service__["a" /* EmailServerService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__emailserver_service__["a" /* EmailServerService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_5__emailserverproperties_service__["a" /* EmailServerPropertiesService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_5__emailserverproperties_service__["a" /* EmailServerPropertiesService */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_6__core_authorization_service__["a" /* AuthorizationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_6__core_authorization_service__["a" /* AuthorizationService */]) === "function" && _c || Object])
], EmailServerComponent);

var _a, _b, _c;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/emailserver.component.js.map

/***/ }),

/***/ "../../../../../src/app/email/server/emailserver.properties.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return EmailServerProperties; });
var EmailServerProperties = (function () {
    function EmailServerProperties() {
    }
    return EmailServerProperties;
}());

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/emailserver.properties.js.map

/***/ }),

/***/ "../../../../../src/app/email/server/emailserver.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__("../../../http/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__ = __webpack_require__("../../../../rxjs/Observable.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return EmailServerService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var EmailServerService = (function () {
    function EmailServerService(http) {
        this.http = http;
        this.emailServerURL = "emailServer";
    }
    EmailServerService.prototype.getAllEmailServers = function () {
        return this.http.get(this.emailServerURL)
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    EmailServerService.prototype.createEmailServer = function (emailServer) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.post(this.emailServerURL, JSON.stringify(emailServer), { headers: headers })
            .map(function (res) { return; })
            .catch(this.handleError);
    };
    EmailServerService.prototype.deleteEmailServer = function (objectId) {
        return this.http.delete(this.emailServerURL + "/" + objectId)
            .map(function (res) { return; })
            .catch(this.handleError);
    };
    EmailServerService.prototype.updateEmailServerSubmit = function (emailServer) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.put(this.emailServerURL + "/" + emailServer.id, JSON.stringify(emailServer), { headers: headers })
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    EmailServerService.prototype.extractData = function (res) {
        var body = res.json();
        return body || {};
    };
    EmailServerService.prototype.handleError = function (error) {
        var errMsg = (error.message) ? error.message :
            error.status ? error.status + " - " + error.statusText : 'Server error';
        //console.error(errMsg);
        return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].throw(errMsg);
    };
    return EmailServerService;
}());
EmailServerService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */]) === "function" && _a || Object])
], EmailServerService);

var _a;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/emailserver.service.js.map

/***/ }),

/***/ "../../../../../src/app/email/server/emailserver.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return EmailServer; });
var EmailServer = (function () {
    function EmailServer() {
    }
    return EmailServer;
}());

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/emailserver.js.map

/***/ }),

/***/ "../../../../../src/app/email/server/emailserverproperties.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__("../../../http/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__ = __webpack_require__("../../../../rxjs/Observable.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return EmailServerPropertiesService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var EmailServerPropertiesService = (function () {
    function EmailServerPropertiesService(http) {
        this.http = http;
        this.emailServerPropertiesURL = "emailServerProperties";
    }
    EmailServerPropertiesService.prototype.getEmailServerPropertiesByEmailServerId = function (emailServerId) {
        return this.http.get(this.emailServerPropertiesURL + '/emailServer/' + emailServerId)
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    EmailServerPropertiesService.prototype.createEmailServerProperty = function (emailServerProperty, emailServerId) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.post(this.emailServerPropertiesURL + "/" + emailServerId, JSON.stringify(emailServerProperty), { headers: headers })
            .map(function (res) { return; })
            .catch(this.handleError);
    };
    EmailServerPropertiesService.prototype.deleteEmailServerProperty = function (objectId) {
        return this.http.delete(this.emailServerPropertiesURL + "/" + objectId)
            .map(function (res) { return; })
            .catch(this.handleError);
    };
    EmailServerPropertiesService.prototype.extractData = function (res) {
        var body = res.json();
        return body || {};
    };
    EmailServerPropertiesService.prototype.handleError = function (error) {
        var errMsg = (error.message) ? error.message :
            error.status ? error.status + " - " + error.statusText : 'Server error';
        console.error(errMsg);
        return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].throw(errMsg);
    };
    return EmailServerPropertiesService;
}());
EmailServerPropertiesService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */]) === "function" && _a || Object])
], EmailServerPropertiesService);

var _a;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/emailserverproperties.service.js.map

/***/ }),

/***/ "../../../../../src/app/email/shared/common.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__contact_contact_service__ = __webpack_require__("../../../../../src/app/email/contact/contact.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__group_group_service__ = __webpack_require__("../../../../../src/app/email/group/group.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__contact_contact_search_criteria__ = __webpack_require__("../../../../../src/app/email/contact/contact_search_criteria.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__group_group_search_criteria__ = __webpack_require__("../../../../../src/app/email/group/group_search_criteria.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return CommonService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var CommonService = (function () {
    function CommonService(contactService, groupService) {
        this.contactService = contactService;
        this.groupService = groupService;
        this.contactSearchCriteria = new __WEBPACK_IMPORTED_MODULE_3__contact_contact_search_criteria__["a" /* ContactSearchCriteria */]();
        this.groupSearchCriteria = new __WEBPACK_IMPORTED_MODULE_4__group_group_search_criteria__["a" /* GroupSearchCriteria */]();
    }
    CommonService.prototype.resetContactsBySearchCriteria = function () {
        this.contacts = [];
        this.contactSearchCriteria = new __WEBPACK_IMPORTED_MODULE_3__contact_contact_search_criteria__["a" /* ContactSearchCriteria */]();
    };
    CommonService.prototype.resetGroupsBySearchCriteria = function () {
        this.groups = [];
        this.groupSearchCriteria = new __WEBPACK_IMPORTED_MODULE_4__group_group_search_criteria__["a" /* GroupSearchCriteria */]();
    };
    CommonService.prototype.getAllContacts = function (username) {
        var _this = this;
        this.contactService.getAllContacts(username)
            .subscribe(function (contacts) {
            _this.contacts = contacts;
            for (var _i = 0, _a = _this.contacts; _i < _a.length; _i++) {
                var contact = _a[_i];
                for (var _b = 0, _c = contact.contactGroups; _b < _c.length; _b++) {
                    var contactGroup = _c[_b];
                    if (contact.groupDetails === undefined) {
                        contact.groupDetails = contactGroup.group.name;
                    }
                    else {
                        contact.groupDetails += ", " + contactGroup.group.name;
                    }
                }
            }
        });
    };
    CommonService.prototype.getAllContactsBySearchCriteria = function (username) {
        var _this = this;
        this.contactService.getAllContactsByCriteria(this.contactSearchCriteria, username)
            .subscribe(function (contacts) {
            _this.contacts = contacts;
            for (var _i = 0, _a = _this.contacts; _i < _a.length; _i++) {
                var contact = _a[_i];
                for (var _b = 0, _c = contact.contactGroups; _b < _c.length; _b++) {
                    var contactGroup = _c[_b];
                    if (contact.groupDetails === undefined) {
                        contact.groupDetails = contactGroup.group.name;
                    }
                    else {
                        contact.groupDetails += ", " + contactGroup.group.name;
                    }
                }
            }
        });
    };
    CommonService.prototype.getAllGroups = function (username) {
        var _this = this;
        this.groupService.getAllGroups(username)
            .subscribe(function (groups) {
            _this.groupItems = [];
            _this.groupNamesForSearch = [];
            for (var _i = 0, groups_1 = groups; _i < groups_1.length; _i++) {
                var group = groups_1[_i];
                _this.groupItems.push({ label: group.name, value: group });
                _this.groupNamesForSearch.push({ label: group.name, value: group.id });
            }
        });
    };
    CommonService.prototype.searchGroupsByCriteria = function (username) {
        var _this = this;
        this.groupService.getAllGroupsBySearchCriteria(this.groupSearchCriteria, username)
            .subscribe(function (groups) { return _this.groups = groups; });
    };
    return CommonService;
}());
CommonService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__contact_contact_service__["a" /* ContactService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__contact_contact_service__["a" /* ContactService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__group_group_service__["a" /* GroupService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__group_group_service__["a" /* GroupService */]) === "function" && _b || Object])
], CommonService);

var _a, _b;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/common.service.js.map

/***/ }),

/***/ "../../../../../src/app/home/home.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../node_modules/css-loader/lib/css-base.js")(false);
// imports
exports.push([module.i, "@import url(https://fonts.googleapis.com/css?family=Montserrat);", ""]);

// module
exports.push([module.i, "md-sidenav-layout {\r\n  overflow:visible;\r\n}\r\nmd-sidenav-layout.m2app-dark {\r\n  background-color: black;\r\n}\r\n\r\n.app-content {\r\n  /*padding: 20px;\r\n  min-height:95vh;*/\r\n  background-size: cover;\r\nbox-shadow: -5px 23px 101px -23px rgba(0,0,0,0.75);\r\n}\r\n\r\n.app-sidenav {\r\n  /*background: white;*/\r\n  /*height: 100vh;*/\r\n  min-height:100vh;\r\n  width:auto;\r\n  height:auto;\r\n}\r\n\r\n.app-sidenav table {\r\n  margin-top:30px;\r\n  margin-left:2px; \r\n padding:30px 5px;\r\n width:100%;\r\n/*-webkit-box-shadow: 6px 18px 20px -23px rgba(0,0,0,0.75);\r\n-moz-box-shadow: 6px 18px 20px -23px rgba(0,0,0,0.75);\r\nbox-shadow: 6px 18px 20px -23px rgba(0,0,0,0.75);*/\r\nmin-height:60vh;\r\n\r\n}\r\n\r\n.app-sidenav table tr {\r\n width:100%;\r\n  padding:30px 5px;\r\n/*-webkit-box-shadow: 6px 18px 20px -23px rgba(0,0,0,0.75);\r\n-moz-box-shadow: 6px 18px 20px -23px rgba(0,0,0,0.75);\r\nbox-shadow: 6px 18px 20px -23px rgba(0,0,0,0.75);*/\r\nborder-bottom:1px solid #e6e9ef;\r\n}\r\n\r\n.app-sidenav table td {\r\n \r\n text-align:left;\r\n color:#797a7c;\r\n}\r\n\r\n.app-sidenav table tr:hover {\r\n\tbackground:#54a0d3;\r\n}\r\n\r\n.app-sidenav table td:hover {\r\n\tcolor:white;\r\n\tfont-weight:bold;\r\n}\r\n\r\n.md-button-ripple::after {\r\nbackground:#54a0d3;\r\ncolor:white;\r\n}\r\n\r\n.app-sidenav table td img {\r\n\twidth:35px;\r\n\theight:35px;\r\n}\r\n\r\n.app-sidenav-links {\r\n  /*background: white;*/\r\n}\r\n\r\n.app-sidenav-links:hover\r\n{\r\n   /*background:white;\r\n   background-color: white;*/\r\n}\r\n\r\n.app-toolbar-filler {\r\n  -webkit-box-flex: 1;\r\n      -ms-flex: 1 1 auto;\r\n          flex: 1 1 auto;\r\n}\r\n\r\n.app-toolbar-menu {\r\n  padding: 10px;\r\n  color: white;\r\n}\r\n\r\n.app-icon-button {\r\n  box-shadow: none;\r\n  -webkit-user-select: none;\r\n     -moz-user-select: none;\r\n      -ms-user-select: none;\r\n          user-select: none;\r\n  background: none;\r\n  border: none;\r\n  cursor: pointer;\r\n  -webkit-filter: none;\r\n          filter: none;\r\n  font-weight: normal;\r\n  height: auto;\r\n  line-height: inherit;\r\n  margin: 0;\r\n  min-width: 0;\r\n  padding: 0;\r\n  text-align: left;\r\n  text-decoration: none;\r\n}\r\n\r\n.app-action {\r\n  display: inline-block;\r\n  position: fixed;\r\n  bottom: 20px;\r\n  right: 20px;\r\n}\r\n\r\n.md-toolbar.md-primary {\r\n  padding-left: 60px;\r\n  padding-right: 90px;\r\n}\r\n\r\n#tool-bt{\r\n  width: 110px;\r\n  font-family: 'Montserrat', sans-serif;\r\n  background-color: #2382f6;\r\n}", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/home/home.component.html":
/***/ (function(module, exports) {

module.exports = "<md-sidenav-layout [class.m2app-dark]=\"isDarkTheme\">\r\n\r\n  <md-sidenav #sidenav mode=\"side\" class=\"app-sidenav\" [opened]=\"userLoggedIn\">\r\n    <md-toolbar color=\"primary\">\r\n      <h3>\r\n        <span><img src=\"../../app/resources/images/logo.png\" style=\"width:35px;height:35px;\"/></span>\r\n      </h3>\r\n    </md-toolbar>\r\n\r\n    <table>\r\n      <tr *ngIf=\"authorizationService.isUserHasRole('UI_ACCESS_DASHBOARD')\">\r\n\t\t<td>\r\n           <img src=\"../../app/resources/images/dashboard.png\"/>\r\n        </td>\r\n        <td>\r\n           <button md-button [routerLink]=\"['dashboard']\" routerLinkActive=\"active\">Dashboard</button>\r\n        </td>\r\n      </tr>\r\n      <tr *ngIf=\"authorizationService.isUserHasRole('UI_ACCESS_CONTACTS')\">\r\n\t\t<td>\r\n           <img src=\"../../app/resources/images/contact.png\"/>\r\n        </td>\r\n        <td>\r\n           <button md-button [routerLink]=\"['contacts']\" routerLinkActive=\"active\">Contacts</button>\r\n        </td>\r\n      </tr>\r\n\t  <tr *ngIf=\"authorizationService.isUserHasRole('UI_ACCESS_BULK_UPLOAD')\">\r\n\t\t<td>\r\n           <img src=\"../../app/resources/images/upload.png\"/>\r\n        </td>\r\n        <td>\r\n           <button md-button [routerLink]=\"['bulk_upload']\" routerLinkActive=\"active\">Bulk Contacts Upload</button>\r\n        </td>\r\n      </tr>\r\n      <tr *ngIf=\"authorizationService.isUserHasRole('UI_ACCESS_GROUPS')\">\r\n\t\t<td>\r\n           <img src=\"../../app/resources/images/usergroup.png\"/>\r\n        </td>\r\n        <td>\r\n           <button md-button [routerLink]=\"['groups']\" routerLinkActive=\"active\">Groups</button>\r\n        </td>\r\n      </tr>\r\n      <tr *ngIf=\"authorizationService.isUserHasRole('UI_ACCESS_SEND_EMAIL')\">\r\n\t\t<td>\r\n           <img src=\"../../app/resources/images/sendemail.png\"/>\r\n        </td>\r\n        <td>\r\n           <button md-button [routerLink]=\"['send_email']\" routerLinkActive=\"active\">Send Emails</button>\r\n        </td>\r\n      </tr>\r\n      <tr *ngIf=\"authorizationService.isUserHasRole('UI_ACCESS_SERVERS')\">\r\n\t\t<td>\r\n           <img src=\"../../app/resources/images/server.png\"/>\r\n        </td>\r\n        <td>\r\n           <button md-button [routerLink]=\"['servers']\" routerLinkActive=\"active\">Servers</button>\r\n        </td>\r\n      </tr>\r\n\t  <tr *ngIf=\"authorizationService.isUserHasRole('UI_ACCESS_ANALYTICS')\">\r\n\t\t    <td>\r\n           <img src=\"../../app/resources/images/analytics.png\"/>\r\n        </td>\r\n        <td>\r\n           <button md-button [routerLink]=\"['analytics']\" routerLinkActive=\"active\">Analytics</button>\r\n        </td>\r\n      </tr>\r\n      <tr *ngIf=\"authorizationService.isUserHasRole('UI_ACCESS_ACCOUNT_APPROVAL')\">\r\n\t\t    <td>\r\n           <img src=\"../../app/resources/images/approve2.png\"/>\r\n        </td>\r\n        <td>\r\n           <button md-button [routerLink]=\"['account_approval']\" routerLinkActive=\"active\">Account Approval</button>\r\n        </td>\r\n      </tr>\r\n      <tr *ngIf=\"authorizationService.isUserHasRole('UI_USER_ROLES_ACCESS')\">\r\n\t\t<td>\r\n           <img src=\"../../app/resources/images/userrole.png\"/>\r\n        </td>\r\n        <td>\r\n           <button md-button [routerLink]=\"['user_roles']\" routerLinkActive=\"active\">User Roles</button>\r\n        </td>\r\n      </tr>\r\n      <tr *ngIf=\"authorizationService.isUserHasRole('UI_USER_GROUPS_ACCESS')\">\r\n\t\t<td>\r\n           <img src=\"../../app/resources/images/usergroup.png\"/>\r\n        </td>\r\n        <td>\r\n           <button md-button [routerLink]=\"['user_groups']\" routerLinkActive=\"active\">User Groups</button>\r\n        </td>\r\n      </tr>\r\n      <tr *ngIf=\"authorizationService.isUserHasRole('UI_USERS_ACCESS')\">\r\n\t\t<td>\r\n           <img src=\"../../app/resources/images/useraccount.png\"/>\r\n        </td>\r\n        <td>\r\n           <button md-button [routerLink]=\"['user_accounts']\" routerLinkActive=\"active\">User Accounts</button>\r\n        </td>\r\n      </tr>\r\n    </table>\r\n  </md-sidenav>\r\n\r\n  <md-toolbar color=\"primary\">\r\n    <button class=\"app-icon-button\" (click)=\"sidenav.toggle()\"  *ngIf=\"userLoggedIn\">\r\n      <i class=\"material-icons app-toolbar-menu\">menu</i>\r\n    </button> <img src=\"../../app/resources/images/white_logo.png\" style=\"height:40px;\">\r\n    <span class=\"app-toolbar-filler\"></span>\r\n\r\n\r\n<button type=\"submit\" class=\"btn btn-primary\" id=\"tool-bt\" style=\"margin-right:20px;\" [routerLink]=\"['/appLogin']\" routerLinkActive=\"active\"\r\n      *ngIf=\"!userLoggedIn\">Sign In</button>\r\n <button type=\"submit\" class=\"btn btn-primary\" id=\"tool-bt\" [routerLink]=\"['/appRegister']\" routerLinkActive=\"active\"\r\n      *ngIf=\"!userLoggedIn\">Sign Up</button>\r\n\r\n<!--\r\n    <button md-button md-raised-button color=\"primary\" [routerLink]=\"['/appLogin']\" routerLinkActive=\"active\"\r\n      *ngIf=\"!userLoggedIn\" style=\"padding-right:10px\">Login</button>\r\n\t  <button md-button md-raised-button color=\"primary\"  [routerLink]=\"['/appRegister']\" routerLinkActive=\"active\"\r\n      *ngIf=\"!userLoggedIn\">Register</button>  \r\n\r\n      -->\r\n\r\n    <div *ngIf=\"userLoggedIn\" style=\"margin-right:30px;\">\r\n      <button md-button [md-menu-trigger-for]=\"menu\" md-raised-button color=\"primary\" *ngIf=\"userLoggedIn\">\r\n        <md-icon>person</md-icon>\r\n        {{user.loggedInUserName}}\r\n      </button>\r\n    </div>\r\n    <md-menu #menu=\"mdMenu\" >\r\n      <button md-menu-item>Preferences</button>\r\n      <button md-menu-item (click)=\"changePassword()\">Change password</button>\r\n      <button md-menu-item (click)=\"logout()\">Logout</button>\r\n    </md-menu>\r\n\r\n  </md-toolbar>\r\n\r\n  <div class=\"app-content\" [style.background-image]=\"backgroundImg\">\r\n    <router-outlet></router-outlet>\r\n  </div>\r\n\r\n</md-sidenav-layout>"

/***/ }),

/***/ "../../../../../src/app/home/home.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__security_login_login_service__ = __webpack_require__("../../../../../src/app/security/login/login.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__core_global_service__ = __webpack_require__("../../../../../src/app/core/global.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__core_authorization_service__ = __webpack_require__("../../../../../src/app/core/authorization.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__angular_platform_browser__ = __webpack_require__("../../../platform-browser/index.js");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return HomeComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var HomeComponent = (function () {
    function HomeComponent(router, loginService, globalService, authorizationService, sanitizer) {
        this.router = router;
        this.loginService = loginService;
        this.globalService = globalService;
        this.authorizationService = authorizationService;
        this.sanitizer = sanitizer;
        this.backgroundImg = sanitizer.bypassSecurityTrustStyle('url(../../app/resources/images/background.jpg)');
    }
    HomeComponent.prototype.ngOnInit = function () {
    };
    Object.defineProperty(HomeComponent.prototype, "userLoggedIn", {
        get: function () {
            //console.log("checking user logged in");
            var userLoggedIn = false;
            if (this.globalService.userLoggedIn) {
                //console.log("globalServic is enabled - "+this.globalService.userLoggedIn);
                userLoggedIn = this.globalService.userLoggedIn;
                return userLoggedIn;
            }
            else if (sessionStorage.getItem('userLoggedIn')) {
                //console.log("************************* gobal service oops.. check sessionStorage : "+sessionStorage.getItem('loggedInUser')+" | "+sessionStorage.getItem('userLoggedIn'));
                this.globalService.userLoggedIn = true;
                this.globalService.loggedInUser = JSON.parse(sessionStorage.getItem('loggedInUser'));
                return (sessionStorage.getItem('userLoggedIn') === 'true');
            }
        },
        enumerable: true,
        configurable: true
    });
    HomeComponent.prototype.logout = function () {
        sessionStorage.clear();
        localStorage.clear();
        this.router.navigate(['']);
        this.loginService.logout().subscribe(function () {
            window.location.reload();
        }, function (error) {
        });
    };
    HomeComponent.prototype.changePassword = function () {
        this.router.navigate(['changePassword']);
    };
    Object.defineProperty(HomeComponent.prototype, "pageLinks", {
        /*analytics() {
            this.router.navigate(['analytics']);
        }	*/
        get: function () {
            /*this._pageLinks = [];
            let pageLink   = new PageLink();
            pageLink.label = "CONTACTS";
            pageLink.url = "contacts";
            this._pageLinks.push(pageLink);
        
            pageLink   = new PageLink();
            pageLink.label = "GROUPS";
            pageLink.url = "groups";
            this._pageLinks.push(pageLink);
        
            pageLink   = new PageLink();
            pageLink.label = "SEND_EMAILS";
            pageLink.url = "send_emails";
            this._pageLinks.push(pageLink); */
            this._pageLinks = this.globalService.pageLinks;
            //console.log(this._pageLinks);
            return this._pageLinks;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(HomeComponent.prototype, "user", {
        get: function () {
            this._user = this.globalService.loggedInUser;
            return this._user;
        },
        enumerable: true,
        configurable: true
    });
    return HomeComponent;
}());
HomeComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'home-comp',
        template: __webpack_require__("../../../../../src/app/home/home.component.html"),
        styles: [__webpack_require__("../../../../../src/app/home/home.component.css")]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["Router"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["Router"]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__security_login_login_service__["a" /* LoginService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__security_login_login_service__["a" /* LoginService */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_3__core_global_service__["a" /* GlobalService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__core_global_service__["a" /* GlobalService */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_4__core_authorization_service__["a" /* AuthorizationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__core_authorization_service__["a" /* AuthorizationService */]) === "function" && _d || Object, typeof (_e = typeof __WEBPACK_IMPORTED_MODULE_5__angular_platform_browser__["DomSanitizer"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_5__angular_platform_browser__["DomSanitizer"]) === "function" && _e || Object])
], HomeComponent);

var _a, _b, _c, _d, _e;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/home.component.js.map

/***/ }),

/***/ "../../../../../src/app/index.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__app_component__ = __webpack_require__("../../../../../src/app/app.component.ts");
/* unused harmony namespace reexport */
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__app_module__ = __webpack_require__("../../../../../src/app/app.module.ts");
/* harmony namespace reexport (by used) */ __webpack_require__.d(__webpack_exports__, "a", function() { return __WEBPACK_IMPORTED_MODULE_1__app_module__["a"]; });


//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/index.js.map

/***/ }),

/***/ "../../../../../src/app/rxjs-extensions.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_rxjs_add_observable_of__ = __webpack_require__("../../../../rxjs/add/observable/of.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_rxjs_add_observable_of___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_rxjs_add_observable_of__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_add_observable_throw__ = __webpack_require__("../../../../rxjs/add/observable/throw.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_add_observable_throw___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_rxjs_add_observable_throw__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_add_operator_catch__ = __webpack_require__("../../../../rxjs/add/operator/catch.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_add_operator_catch___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_add_operator_catch__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_debounceTime__ = __webpack_require__("../../../../rxjs/add/operator/debounceTime.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_debounceTime___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_debounceTime__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_distinctUntilChanged__ = __webpack_require__("../../../../rxjs/add/operator/distinctUntilChanged.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_distinctUntilChanged___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_distinctUntilChanged__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_rxjs_add_operator_do__ = __webpack_require__("../../../../rxjs/add/operator/do.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_rxjs_add_operator_do___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_5_rxjs_add_operator_do__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_rxjs_add_operator_filter__ = __webpack_require__("../../../../rxjs/add/operator/filter.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_rxjs_add_operator_filter___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_6_rxjs_add_operator_filter__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7_rxjs_add_operator_map__ = __webpack_require__("../../../../rxjs/add/operator/map.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7_rxjs_add_operator_map___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_7_rxjs_add_operator_map__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8_rxjs_add_operator_switchMap__ = __webpack_require__("../../../../rxjs/add/operator/switchMap.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8_rxjs_add_operator_switchMap___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_8_rxjs_add_operator_switchMap__);
// Observable class extensions


// Observable operators







//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/rxjs-extensions.js.map

/***/ }),

/***/ "../../../../../src/app/security/account-approval/accountapproval.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../node_modules/css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "#mainContainer\r\n{\r\n    width:95%;\r\n}\r\n\r\n#pendingapprovaltable\r\n{\r\n    padding:10px;\r\n     width:95%;\r\n}\r\n\r\n#pendingapprovaltable table {\r\n    min-width: 92%;\r\n    box-shadow: -4px 10px 25px -6px rgba(0,0,0,0.75);\r\n}\r\n\r\n#pendingapprovaltable table thead tr {\r\n\tbackground-color:#604688;\r\n\tcolor:white;\r\n    text-align:center;\r\n}\r\n\r\ntable tr\r\n{\r\n    padding:7px;\r\n}\r\n\r\n#pendingapprovaltable table thead tr td {\r\n    border-right:1px solid #ffffff;\r\n    padding:10px;\r\n}\r\n\r\n#pendingapprovaltable table tr td {\r\n\tpadding:3px;\r\n    text-align:center;\r\n    border-right:1px solid #BDBDBE;\r\n}\r\n\r\nbutton{\r\n    width:90%;\r\n    height:80%;\r\n    overflow:hidden;\r\n}\r\n\r\nmd-card {\r\n\t\t/*-webkit-box-shadow: -4px 10px 25px -6px rgba(0,0,0,0.75);\r\n-moz-box-shadow: -4px 10px 25px -6px rgba(0,0,0,0.75);\r\nbox-shadow: -4px 10px 25px -6px rgba(0,0,0,0.75);\r\nmargin-top:2vh;\r\nbackground:white;\r\nborder-radius: 12px 12px 12px 12px;\r\n-moz-border-radius: 12px 12px 12px 12px;\r\n-webkit-border-radius: 12px 12px 12px 12px;\r\nborder: 0px solid #000000;*/\r\nmin-height:25vh;\r\nwidth:105%;\r\n}", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/security/account-approval/accountapproval.component.html":
/***/ (function(module, exports) {

module.exports = "<p-growl [value]=\"msgs\"></p-growl>\r\n<form (ngSubmit)=\"onSubmit()\" autocomplete=\"off\" novalidate #approvalForm=\"ngForm\">  \r\n<div id=\"mainContainer\">\r\n    <md-card class=\"app-input-section\" style=\"width:94%;\">\r\n        <md-card-title>\r\n            <span><img src=\"../../app/resources/images/approval.png\" style=\"width:60px;height:60px;margin-right:15px;opacity:100;\"/></span><span>Account approval requests</span>\r\n        </md-card-title>\r\n        <md-card-content>\r\n             <p> Following account creation requests are waiting for your approval </p>\r\n\r\n        <div id=\"pendingapprovaltable\">\r\n            <table>\r\n                <thead>\r\n                    <tr>\r\n                        <td>Serial No</td>\r\n                        <td>user ID</td>\r\n                        <td>Name</td>\r\n                        <td>Email</td>\r\n\t\t\t\t\t\t<td>Company Name</td>\r\n                        <td>Requested On</td>\r\n                        <td>Address</td>\r\n                        <td>City</td>\r\n                        <td>State</td>\r\n                        <td>Country</td>\r\n                        <td>Zipcode</td>\r\n                        <td>Current Status</td>\r\n                        <td colspan=\"3\">Action</td>\r\n                    </tr>\r\n                </thead>\r\n                <tbody>\r\n                    <tr *ngIf=\"noDataFound\">\r\n                        <td colspan=\"15\">No requests pending for your approval</td>            \r\n                    </tr>\r\n                        <tr *ngFor=\"let data of pendingApprovals\">\r\n                            <td>{{data.serialNo}}</td>\r\n                            <td>{{data.id}}</td>\r\n                            <td>{{data.name}}</td>\r\n                            <td>{{data.email}}</td>\r\n\t\t\t\t\t\t\t<td>{{data.companyName}}</td>\r\n                            <td>{{data.registrationRequestDate}}</td>\r\n                            <td>{{data.address}}</td>\r\n                            <td>{{data.city}}</td>\r\n                            <td>{{data.state}}</td>\r\n                            <td>{{data.country}}</td>\r\n                            <td>{{data.zipcode}}</td>\r\n                            <td>{{data.status}}</td>\r\n                            <td>\r\n                                <!--button md-button type=\"submit\" (click)=\"setOnHoldRequestData(data.id)\" [disabled]=\"disabled\" md-raised-button color=\"primary\" >Approve</button-->  \r\n                                 <button type=\"submit\" class=\"btn btn-success btn-s\" (click)=\"setApproveRequestData(data.id)\" [disabled]=\"disabled\">\r\n                                            <span class=\"glyphicon glyphicon-upload\"></span> Approve\r\n                                        </button>\r\n                            </td>\r\n                            <td>\t\r\n                                <button type=\"submit\" class=\"btn btn-warning btn-s\" (click)=\"setOnHoldRequestData(data.id)\" [disabled]=\"data.onHold\">\r\n                                            <span class=\"glyphicon glyphicon-upload\"></span> On Hold\r\n                                        </button>\r\n                            </td>\r\n                            <td>\r\n                                <button type=\"submit\" class=\"btn btn-danger btn-s\"\r\n                                                (click)=\"setRejectRequestData(data.id)\" [disabled]=\"data.rejected\">\r\n                                            <span class=\"glyphicon glyphicon-ban-circle\"></span> Reject\r\n                                </button>\r\n                            </td>\r\n                        </tr>\r\n                </tbody>\r\n\r\n            </table>\r\n        </div>\r\n        </md-card-content>\r\n    </md-card>\r\n</div>\r\n  <div class=\"col-xs-1 col-sm-3 col-md-4 col-lg-4 col-xl-4\"></div>\r\n   <!--p-dialog header=\"Status...\" [(visible)]=\"approved\"  modal=\"modal\" (onAfterShow)=\"approve()\" minWidth=\"200px\">\r\n    </p-dialog-->\r\n</form>\r\n"

/***/ }),

/***/ "../../../../../src/app/security/account-approval/accountapproval.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__accountapproval_service__ = __webpack_require__("../../../../../src/app/security/account-approval/accountapproval.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_router__ = __webpack_require__("../../../router/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__core_global_service__ = __webpack_require__("../../../../../src/app/core/global.service.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AccountApprovalComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var AccountApprovalComponent = (function () {
    //user:User;
    function AccountApprovalComponent(router, accountAppovalService, globalService) {
        this.router = router;
        this.accountAppovalService = accountAppovalService;
        this.globalService = globalService;
        this.msgs = [];
        this.noDataFound = false;
        if (sessionStorage.getItem("userLoggedIn") !== 'true') {
            console.log("user not logged in.. routing to login");
            this.router.navigate(['/appLogin']);
        }
        //console.log("activation approal component");
        this.userName = this.globalService.loggedInUser.loggedInUserName;
        //this.userName = this.user.loggedInUserName;
        //console.log("calling get pending approvals for "+this.userName);
        this.getPendingApprovals(this.userName);
    }
    AccountApprovalComponent.prototype.ngOnInit = function () {
        // console.log('checking loggedin in init');
        if (sessionStorage.getItem("userLoggedIn") !== 'true') {
            //console.log("[Init] - user not logged in.. routing to login");
            this.router.navigate(['/appLogin']);
        }
    };
    AccountApprovalComponent.prototype.onSubmit = function () {
        this.submitted = true;
        this.disabled = true;
        if (this.request_type === 'APPROVE') {
            this.approveRequest(this.idToApprove);
        }
        else if (this.request_type === 'HOLD') {
            this.holdRequest(this.idToApprove);
        }
        else if (this.request_type === 'REJECT') {
            this.rejectRequest(this.idToApprove);
        }
    };
    AccountApprovalComponent.prototype.approveRequest = function (idToApprove) {
        var _this = this;
        //console.log("calling approve service");
        this.accountAppovalService.approveRequest(idToApprove.toString())
            .subscribe(function (data) {
            //console.log("Approval Response : "+data);
            _this.msgs.push({ severity: "info", summary: "Registration Approved", detail: "Approval Request processed successfully" });
            _this.approved = false;
            _this.disabled = false;
            _this.getPendingApprovals(_this.userName);
        }, function (error) {
            console.log(error);
            _this.msgs.push({ severity: "error", summary: "Failed to process request", detail: error });
            _this.approved = false;
            _this.disabled = false;
        });
    };
    AccountApprovalComponent.prototype.holdRequest = function (idToApprove) {
        var _this = this;
        console.log("calling hold service");
        this.accountAppovalService.holdRequest(idToApprove.toString())
            .subscribe(function (data) {
            //console.log("Hold Response : "+data);
            _this.msgs.push({ severity: "info", summary: "Registration On Hold", detail: "Approval Request processed successfully" });
            _this.approved = false;
            _this.disabled = false;
            _this.getPendingApprovals(_this.userName);
        }, function (error) {
            console.log(error);
            _this.msgs.push({ severity: "error", summary: "Failed to process request", detail: error });
            _this.approved = false;
            _this.disabled = false;
        });
    };
    AccountApprovalComponent.prototype.rejectRequest = function (idToApprove) {
        var _this = this;
        //console.log("calling reject service");
        this.accountAppovalService.rejectRequest(idToApprove.toString())
            .subscribe(function (data) {
            console.log("Reject Response : " + data);
            _this.msgs.push({ severity: "info", summary: "Registration Rejected", detail: "Approval Request processed successfully" });
            _this.approved = false;
            _this.disabled = false;
            _this.getPendingApprovals(_this.userName);
        }, function (error) {
            console.log(error);
            _this.msgs.push({ severity: "error", summary: "Failed to process request", detail: error });
            _this.approved = false;
            _this.disabled = false;
        });
    };
    AccountApprovalComponent.prototype.showDialog = function () {
        this.approved = true;
    };
    AccountApprovalComponent.prototype.setApproveRequestData = function (idToApprove) {
        this.idToApprove = idToApprove;
        //console.log("input params set : "+this.idToApprove);
        this.request_type = 'APPROVE';
    };
    AccountApprovalComponent.prototype.setOnHoldRequestData = function (idToApprove) {
        this.idToApprove = idToApprove;
        //console.log("input params set : "+this.idToApprove);
        this.request_type = 'HOLD';
    };
    AccountApprovalComponent.prototype.setRejectRequestData = function (idToApprove) {
        this.idToApprove = idToApprove;
        //console.log("input params set : "+this.idToApprove);
        this.request_type = 'REJECT';
    };
    AccountApprovalComponent.prototype.getPendingApprovals = function (userName) {
        var _this = this;
        //console.log("caling service for approval account");
        this.pendingApprovals = [];
        this.accountAppovalService.getPendingApprovals(userName)
            .subscribe(function (data) {
            if (data.length === 0) {
                _this.noDataFound = true;
                //console.log("No requests pending for approval");
            }
            else {
                _this.pendingApprovals = data;
                //console.log(this.pendingApprovals);
            }
        }, function (error) {
            console.log(error);
            _this.noDataFound = true;
        });
    };
    ;
    return AccountApprovalComponent;
}());
AccountApprovalComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'approval',
        template: __webpack_require__("../../../../../src/app/security/account-approval/accountapproval.component.html"),
        styles: [__webpack_require__("../../../../../src/app/security/account-approval/accountapproval.component.css"), __webpack_require__("../../../../../src/app/email/contact/fileupload.component.css")],
        providers: [__WEBPACK_IMPORTED_MODULE_1__accountapproval_service__["a" /* AccountApprovalService */]]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_2__angular_router__["Router"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_router__["Router"]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_1__accountapproval_service__["a" /* AccountApprovalService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__accountapproval_service__["a" /* AccountApprovalService */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_3__core_global_service__["a" /* GlobalService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__core_global_service__["a" /* GlobalService */]) === "function" && _c || Object])
], AccountApprovalComponent);

var _a, _b, _c;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/accountapproval.component.js.map

/***/ }),

/***/ "../../../../../src/app/security/account-approval/accountapproval.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__("../../../http/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__ = __webpack_require__("../../../../rxjs/Observable.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AccountApprovalService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var AccountApprovalService = (function () {
    function AccountApprovalService(http) {
        this.http = http;
    }
    AccountApprovalService.prototype.getPendingApprovals = function (userName) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* RequestOptions */]({ headers: headers });
        var urlSearchParams = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["e" /* URLSearchParams */]();
        urlSearchParams.append('userName', userName);
        var body = urlSearchParams.toString();
        return this.http.post('accounts/getPendingApprovals', body, options)
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
        ;
    };
    AccountApprovalService.prototype.approveRequest = function (idToApprove) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* RequestOptions */]({ headers: headers });
        var urlSearchParams = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["e" /* URLSearchParams */]();
        urlSearchParams.append('id', idToApprove);
        urlSearchParams.append('type', 'APPROVE');
        var body = urlSearchParams.toString();
        return this.http.post('accounts/approveRequest', body, options)
            .map(function (res) { return; })
            .catch(this.handleError);
        ;
    };
    AccountApprovalService.prototype.holdRequest = function (idToApprove) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* RequestOptions */]({ headers: headers });
        var urlSearchParams = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["e" /* URLSearchParams */]();
        urlSearchParams.append('id', idToApprove);
        urlSearchParams.append('type', 'hold');
        var body = urlSearchParams.toString();
        return this.http.post('accounts/approveRequest', body, options)
            .map(function (res) { return; })
            .catch(this.handleError);
        ;
    };
    AccountApprovalService.prototype.rejectRequest = function (idToApprove) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* RequestOptions */]({ headers: headers });
        var urlSearchParams = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["e" /* URLSearchParams */]();
        urlSearchParams.append('id', idToApprove);
        urlSearchParams.append('type', 'reject');
        var body = urlSearchParams.toString();
        return this.http.post('accounts/approveRequest', body, options)
            .map(function (res) { return; })
            .catch(this.handleError);
        ;
    };
    AccountApprovalService.prototype.handleError = function (error) {
        // In a real world app, we might use a remote logging infrastructure
        //console.log(error);
        var errMsg;
        if (error instanceof __WEBPACK_IMPORTED_MODULE_1__angular_http__["f" /* Response */]) {
            var body = error.json() || '';
            var err = body.error || JSON.stringify(body);
            errMsg = error.status + " - " + (error.statusText || '') + " " + err;
        }
        else {
            errMsg = error.message ? error.message : error.toString();
        }
        //console.error(errMsg);
        return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].throw(errMsg);
    };
    return AccountApprovalService;
}());
AccountApprovalService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */]) === "function" && _a || Object])
], AccountApprovalService);

var _a;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/accountapproval.service.js.map

/***/ }),

/***/ "../../../../../src/app/security/change-password/change-password.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../node_modules/css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/security/change-password/change-password.component.html":
/***/ (function(module, exports) {

module.exports = " <p-growl [value]=\"msgs\"></p-growl>\r\n <div class=\"col-xs-1 col-sm-3 col-md-4 col-lg-4 col-xl-4\"></div>\r\n  <div  class=\"col-xs-8 col-sm-6 col-md-4 col-lg-4 col-xl-4\">\r\n    <md-card class=\"app-input-section\">\r\n      <md-card-title>Change password</md-card-title>\r\n      <md-card-content>\r\n        <table style=\"width: 100%\" cellspacing=\"0\">\r\n          <tr>\r\n            <td>\r\n              <md-input placeholder=\"Old Password\" type=\"password\" required [(ngModel)]=\"userAccountChangePasswordResource.oldPassword\" name=\"oldPassword\" style=\"width: 100%\">\r\n              </md-input>\r\n            </td>\r\n          </tr>\r\n          <tr>\r\n            <td>\r\n              <md-input placeholder=\"New Password\" required type=\"password\" [(ngModel)]=\"userAccountChangePasswordResource.newPassword\" name=\"newPassword\" style=\"width: 100%\">\r\n              </md-input>\r\n            </td>\r\n          </tr>\r\n          <tr>\r\n            <td>\r\n              <md-input placeholder=\"Confirm Password\" required type=\"password\" [(ngModel)]=\"userAccountChangePasswordResource.confirmPassword\" name=\"confirmPassword\" style=\"width: 100%\">\r\n              </md-input>\r\n            </td>\r\n          </tr>\r\n        </table>\r\n      </md-card-content>\r\n      <md-card-actions>\r\n        <button md-button md-raised-button color=\"primary\" (click)=\"submitChangePassword()\">Submit</button>\r\n        <button md-button md-raised-button color=\"primary\" (click)=\"cancel()\">Cancel</button>\r\n      </md-card-actions>\r\n    </md-card>\r\n  </div>\r\n  <div class=\"col-xs-1 col-sm-3 col-md-4 col-lg-4 col-xl-4\"></div>"

/***/ }),

/***/ "../../../../../src/app/security/change-password/change-password.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__model_user_account_change_password_resource__ = __webpack_require__("../../../../../src/app/security/model/user_account_change_password_resource.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__user_account_user_account_service__ = __webpack_require__("../../../../../src/app/security/user-account/user-account.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_router__ = __webpack_require__("../../../router/index.js");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ChangePasswordComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var ChangePasswordComponent = (function () {
    function ChangePasswordComponent(router, userAccountService) {
        this.router = router;
        this.userAccountService = userAccountService;
        this.msgs = [];
        this.userAccountChangePasswordResource = new __WEBPACK_IMPORTED_MODULE_1__model_user_account_change_password_resource__["a" /* UserAccountChangePasswordResource */]();
    }
    ChangePasswordComponent.prototype.ngOnInit = function () {
    };
    ChangePasswordComponent.prototype.cancel = function () {
        this.router.navigate(['dashboard']);
    };
    ChangePasswordComponent.prototype.submitChangePassword = function () {
        var _this = this;
        this.msgs = [];
        this.userAccountService.changeUserPassword(this.userAccountChangePasswordResource)
            .subscribe(function () {
            _this.userAccountChangePasswordResource = new __WEBPACK_IMPORTED_MODULE_1__model_user_account_change_password_resource__["a" /* UserAccountChangePasswordResource */]();
            _this.msgs.push({ severity: "info", summary: "Password changed successfully.", detail: "" });
        }, function (error) {
            _this.msgs.push({ severity: "error", summary: "Password updation failed.", detail: error });
        });
    };
    ;
    return ChangePasswordComponent;
}());
ChangePasswordComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-change-password',
        template: __webpack_require__("../../../../../src/app/security/change-password/change-password.component.html"),
        styles: [__webpack_require__("../../../../../src/app/security/change-password/change-password.component.css")]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_3__angular_router__["Router"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__angular_router__["Router"]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__user_account_user_account_service__["a" /* UserAccountService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__user_account_user_account_service__["a" /* UserAccountService */]) === "function" && _b || Object])
], ChangePasswordComponent);

var _a, _b;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/change-password.component.js.map

/***/ }),

/***/ "../../../../../src/app/security/change-password/index.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__change_password_component__ = __webpack_require__("../../../../../src/app/security/change-password/change-password.component.ts");
/* harmony namespace reexport (by used) */ __webpack_require__.d(__webpack_exports__, "a", function() { return __WEBPACK_IMPORTED_MODULE_0__change_password_component__["a"]; });

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/index.js.map

/***/ }),

/***/ "../../../../../src/app/security/login/index.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__login_component__ = __webpack_require__("../../../../../src/app/security/login/login.component.ts");
/* harmony namespace reexport (by used) */ __webpack_require__.d(__webpack_exports__, "a", function() { return __WEBPACK_IMPORTED_MODULE_0__login_component__["a"]; });

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/index.js.map

/***/ }),

/***/ "../../../../../src/app/security/login/login.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../node_modules/css-loader/lib/css-base.js")(false);
// imports
exports.push([module.i, "@import url(https://fonts.googleapis.com/css?family=Montserrat);", ""]);

// module
exports.push([module.i, ".centered {\r\n  position: fixed;\r\n  top: 50%;\r\n  left: 50%;\r\n  -webkit-transform: translate(-50%, -50%);\r\n          transform: translate(-50%, -50%);\r\n}\r\n\r\nmd-card {\r\n\t/*\t-webkit-box-shadow: -4px 10px 25px -6px rgba(0,0,0,0.75);\r\n-moz-box-shadow: -4px 10px 25px -6px rgba(0,0,0,0.75);\r\nbox-shadow: -4px 10px 25px -6px rgba(0,0,0,0.75);*/\r\nmargin-top:2vh;\r\nbackground:white;\r\n/*border-radius: 12px 12px 12px 12px;\r\n-moz-border-radius: 12px 12px 12px 12px;\r\n-webkit-border-radius: 12px 12px 12px 12px;*/\r\nborder: 0px solid #000000;\r\nmin-height:25vh;\r\nwidth: 550px;\r\nleft: -35px;\r\n}\r\n\r\n.login{\r\n      padding-left: 20%;\r\n    padding-right: 20%;\r\n    margin-bottom: 30px;\r\n}\r\n\r\n.login-logo{\r\n  padding-right: 20%;\r\n  margin-bottom: 20px;\r\n  margin-top: 20px;\r\n}\r\n\r\n.login{\r\n  \r\n  height: 75px;\r\n}\r\n\r\n.button-class{\r\n  padding-left: 20%;\r\n  padding-right: 20%;\r\n  margin-top: 70px;\r\n  margin-bottom: 10px;\r\n\r\n}\r\n\r\n#submit{\r\n  width: 100%;\r\n}\r\n\r\n#submit:hover{\r\n  background: rgb(200, 200, 200);\r\n  color: #000000;\r\n}\r\n\r\n#back-button{\r\n  padding-left: 20px;\r\n  color: #dedede;\r\n}\r\n\r\n#back-button:hover{\r\n  color: #00aeee;\r\n}\r\n\r\n.submit-bt{\r\n  letter-spacing: 1px;\r\n  font-weight: bolder;\r\n}\r\n\r\n#login-area{\r\n  background: #fff;\r\n  min-height: 620px;\r\n}\r\n\r\n\r\n#login-left{\r\n  padding-left: 0px !important;\r\n  padding-right: 0px !important;\r\n  height: 82vh;\r\n   background-image:url('/../../app/resources/images/content/cover.jpg');\r\n    background-repeat: no-repeat;\r\n    background-size: cover;\r\n}\r\n\r\n#login-page{\r\n   padding-left: 0px !important;\r\n  padding-right: 0px !important;\r\n  padding-bottom: 73px;\r\n}\r\n\r\n.login-content{\r\n  padding-top: 20px;\r\n}\r\n\r\n.login-content input{\r\n  height: 45px;\r\n  font-size: large;\r\n  font-family:'Montserrat', sans-serif;\r\n  width: 500px;\r\n}\r\n\r\n.head-label{\r\n  font-family:'Montserrat', sans-serif;\r\n  margin-bottom: 30px;\r\n    padding-left: 20px;\r\n}\r\n\r\n.login-form{\r\n  padding-left: 20px;\r\n}\r\n\r\n#sub-button{\r\n      width: 500px;\r\n    height: 40px;\r\n    font-size: large;\r\n    font-family: 'Montserrat', sans-serif;\r\n    background-color: #00aeee;\r\n}\r\n\r\n.form-hr{\r\n      margin-top: 30px;\r\n    margin-bottom: 30px;\r\n    border: 0;\r\n    border-top: 2px solid #eee;\r\n}\r\n\r\n.footer{\r\n  display: table;\r\n    background-color: #1b3049;\r\n    height: 75px;\r\n    position: fixed;\r\n    bottom: 0;\r\n    left: 0;\r\n    width: 100%;\r\n}\r\n\r\n.footer p{\r\n      color: #fff;\r\n    padding-top: 25px;\r\n    float: right;\r\n    padding-right: 60px;\r\n}\r\n\r\na:hover{\r\n  cursor: pointer;\r\n}", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/security/login/login.component.html":
/***/ (function(module, exports) {

module.exports = "<p-growl [value]=\"msgs\"></p-growl>\r\n<div class=\"col-md-12\" id=\"login-page\">\r\n<div class=\"col-md-7\" id=\"login-left\">\r\n\r\n</div>\r\n\r\n<div class=\"col-md-5\" id=\"login-area\">\r\n  \r\n<div class=\"login-content\">\r\n  <a class=\"btn-floating btn-large waves-effect waves-light red\" id=\"back-button\" (click)=home()><i class=\"material-icons\"><i class=\"fa fa-arrow-left\" aria-hidden=\"true\"></i></i></a>\r\n<form (ngSubmit)=\"onSubmit()\" autocomplete=\"off\" novalidate #loginForm=\"ngForm\">\r\n  \r\n  <img class=\"login\" src=\"../../app/resources/images/logo.png\">\r\n  <h2 class=\"head-label\">Log in</h2>\r\n  <div class=\"login-form\">\r\n  <div class=\"form-group\">\r\n    <input type=\"text\" class=\"form-control\" id=\"username\" aria-describedby=\"username\" name=\"username\" placeholder=\"Username\" required [(ngModel)]=\"username\">\r\n  </div>\r\n  <div class=\"form-group\">\r\n    <input type=\"password\" class=\"form-control\" id=\"password\" placeholder=\"Password\" name=\"password\" required [(ngModel)]=\"password\">\r\n  </div>\r\n  \r\n  <button type=\"submit\" class=\"btn btn-primary\" id=\"sub-button\" (click)=\"showDialog()\" [disabled]=\"!loginForm.form.valid\">Sign In</button>\r\n  </div>\r\n</form>\r\n<hr class=\"form-hr\">\r\n<p style=\"font-size:17px; padding-bottom:10px;\">Forgot your<a> username</a> or <a>password?</a></p>\r\n<p>Don't have an account?<a (click)=appregister() > Sign up free.</a></p>\r\n\r\n</div>\r\n\r\n\r\n    \r\n    <!--\r\n<form (ngSubmit)=\"onSubmit()\" autocomplete=\"off\" novalidate #loginForm=\"ngForm\">\r\n  <div class=\"col-xs-1 col-sm-3 col-md-4 col-lg-4 col-xl-4\"></div>\r\n  <div class=\"col-xs-8 col-sm-6 col-md-4 col-lg-4 col-xl-4\">\r\n    <md-card class=\"app-input-section\">\r\n      <a class=\"btn-floating btn-large waves-effect waves-light red\" id=\"back-button\" (click)=home()><i class=\"material-icons\"><i class=\"fa fa-arrow-left\" aria-hidden=\"true\"></i></i></a>\r\n     <!-- <button md-button md-raised-button color=\"primary\" (click)=home()><i class=\"fa fa-arrow-left\" aria-hidden=\"true\"></i></button> -->\r\n    <!-- <md-card-title class=\"login-logo\"><img class=\"login\" src=\"../../app/resources/images/logo.png\"></md-card-title>\r\n      <md-card-content class=\"login\">\r\n        <table style=\"width: 100%\" cellspacing=\"0\">\r\n          <tr>\r\n            <td>\r\n              <md-input placeholder=\"User name\" required [(ngModel)]=\"username\" name=\"username\" style=\"width: 100%\">\r\n                <span md-prefix>\r\n                <i class=\"material-icons app-input-icon\">person</i>\r\n              </span>\r\n              </md-input>\r\n            </td>\r\n          </tr>\r\n          <tr>\r\n            <td>\r\n              <md-input placeholder=\"Password\" required type=\"password\" [(ngModel)]=\"password\" name=\"password\" style=\"width: 100%\">\r\n                <span md-prefix>\r\n                <i class=\"material-icons app-input-icon\">vpn_key</i>\r\n              </span>\r\n              </md-input>\r\n            </td>\r\n          </tr>\r\n        </table>\r\n      </md-card-content>\r\n      <md-card-actions class=\"button-class\">\r\n        <button md-button type=\"submit\" (click)=\"showDialog()\" md-raised-button color=\"primary\" id=\"submit\" [disabled]=\"!loginForm.form.valid\"><span class=\"submit-bt\">SUBMIT</span></button>\r\n             \r\n      </md-card-actions>\r\n    </md-card>\r\n  </div>\r\n  <div class=\"col-xs-1 col-sm-3 col-md-4 col-lg-4 col-xl-4\"></div>\r\n   <!--p-dialog header=\"Status...\" [(visible)]=\"loggedin\"  modal=\"modal\" (onAfterShow)=\"login()\" minWidth=\"200px\">\r\n    </p-dialog-->\r\n <!--</form> -->\r\n\r\n\r\n \r\n</div>\r\n</div>\r\n\r\n<div class=\"col-md-12\">\r\n  <div class=\"footer\">\r\n    <p>&copy; 2017 Contact Swing</p>\r\n  </div>\r\n</div>\r\n\r\n\r\n"

/***/ }),

/***/ "../../../../../src/app/security/login/login.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__login_service__ = __webpack_require__("../../../../../src/app/security/login/login.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_router__ = __webpack_require__("../../../router/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__core_global_service__ = __webpack_require__("../../../../../src/app/core/global.service.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return LoginComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var LoginComponent = (function () {
    function LoginComponent(loginService, router, globalService) {
        this.loginService = loginService;
        this.router = router;
        this.globalService = globalService;
        this.submitted = false;
        this.msgs = [];
        this.loggedin = false;
    }
    LoginComponent.prototype.ngOnInit = function () {
    };
    LoginComponent.prototype.onSubmit = function () {
        this.submitted = true;
        this.login(this.username, this.password);
    };
    LoginComponent.prototype.getUserName = function () {
    };
    LoginComponent.prototype.login = function (username, password) {
        var _this = this;
        this.loginService.login(this.username, this.password)
            .subscribe(function (data) {
            //console.log("Raw res : "+data.loggedInUserName+data.uiRoles+data.userType);
            _this.user = data;
            //console.log("User Data : "+this.user.uiRoles+ " | "+this.user.userType+' | '+this.user);
            _this.loggedInUser();
        }, function (error) {
            //alert("Login failed: "+error);
            _this.msgs.push({ severity: "error", summary: "Login failed", detail: error });
            _this.loggedin = false;
        });
    };
    ;
    LoginComponent.prototype.home = function () {
        this.router.navigate(['/']);
    };
    LoginComponent.prototype.showDialog = function () {
        this.loggedin = true;
    };
    LoginComponent.prototype.loggedInUser = function () {
        var _this = this;
        this.loginService.loggedInUser()
            .subscribe(function (user) {
            //console.log('user from server ',user);
            //console.log('Stringified user',JSON.stringify(user));
            _this.globalService.loggedInUser = user;
            sessionStorage.setItem('loggedInUser', JSON.stringify(user));
            _this.globalService.userLoggedIn = true;
            sessionStorage.setItem('userLoggedIn', 'true');
            //console.log("User Authenticated.. Setting session storage and Calling pagelinks");
            _this.pageLinksAllowedForUser();
        }, function (error) {
        });
    };
    ;
    LoginComponent.prototype.pageLinksAllowedForUser = function () {
        var _this = this;
        this.loginService.pageLinksAllowedForUser()
            .subscribe(function (pageLinks) {
            console.log('pageLinks from user ', pageLinks);
            _this.globalService.pageLinks = pageLinks;
            sessionStorage.setItem('pageLinks', JSON.stringify(pageLinks));
            _this.router.navigate(['/']);
            _this.router.navigate(['dashboard']);
        }, function (error) {
            console.log("Error page links : " + error);
        });
    };
    ;
    return LoginComponent;
}());
LoginComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'login',
        template: __webpack_require__("../../../../../src/app/security/login/login.component.html"),
        styles: [__webpack_require__("../../../../../src/app/security/login/login.component.css")]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__login_service__["a" /* LoginService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__login_service__["a" /* LoginService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__angular_router__["Router"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_router__["Router"]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_3__core_global_service__["a" /* GlobalService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__core_global_service__["a" /* GlobalService */]) === "function" && _c || Object])
], LoginComponent);

var _a, _b, _c;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/login.component.js.map

/***/ }),

/***/ "../../../../../src/app/security/login/login.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__("../../../http/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__ = __webpack_require__("../../../../rxjs/Observable.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return LoginService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var LoginService = (function () {
    function LoginService(http) {
        this.http = http;
    }
    LoginService.prototype.login = function (userName, password) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* RequestOptions */]({ headers: headers });
        var urlSearchParams = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["e" /* URLSearchParams */]();
        urlSearchParams.append('username', userName);
        urlSearchParams.append('password', password);
        var body = urlSearchParams.toString();
        return this.http.post('login', body, options).catch(this.handleError);
    };
    LoginService.prototype.loggedInUser = function () {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* RequestOptions */]({ headers: headers });
        return this.http.get('user')
            .map(function (res) { return res.json(); }).catch(this.handleError);
        ;
    };
    LoginService.prototype.logout = function () {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* RequestOptions */]({ headers: headers });
        return this.http.get('user/logout').catch(this.handleError);
    };
    LoginService.prototype.pageLinksAllowedForUser = function () {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* RequestOptions */]({ headers: headers });
        return this.http.get('pageLinks')
            .map(function (res) { return res.json(); }).catch(this.handleError);
        ;
    };
    LoginService.prototype.extractData = function (res) {
        var body = res.json();
        return body.data || {};
    };
    LoginService.prototype.handleError = function (error) {
        // In a real world app, we might use a remote logging infrastructure
        //console.log(error);
        var errMsg;
        if (error instanceof __WEBPACK_IMPORTED_MODULE_1__angular_http__["f" /* Response */]) {
            var body = error.json() || '';
            var err = body.error || JSON.stringify(body);
            errMsg = error.status + " - " + (error.statusText || '') + " " + err;
        }
        else {
            errMsg = error.message ? error.message : error.toString();
        }
        //console.error(errMsg);
        return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].throw(errMsg);
    };
    return LoginService;
}());
LoginService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */]) === "function" && _a || Object])
], LoginService);

var _a;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/login.service.js.map

/***/ }),

/***/ "../../../../../src/app/security/model/UserAccountSearchCriteria.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserAccountSearchCriteria; });
var UserAccountSearchCriteria = (function () {
    function UserAccountSearchCriteria() {
    }
    return UserAccountSearchCriteria;
}());

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/UserAccountSearchCriteria.js.map

/***/ }),

/***/ "../../../../../src/app/security/model/item.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Item; });
var Item = (function () {
    function Item() {
    }
    return Item;
}());

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/item.js.map

/***/ }),

/***/ "../../../../../src/app/security/model/user.role.search.criteria.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserRoleSearchCriteria; });
var UserRoleSearchCriteria = (function () {
    function UserRoleSearchCriteria() {
    }
    return UserRoleSearchCriteria;
}());

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/user.role.search.criteria.js.map

/***/ }),

/***/ "../../../../../src/app/security/model/user.role.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__core_model_base_entity__ = __webpack_require__("../../../../../src/app/core/model/base.entity.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserRole; });
var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var UserRole = (function (_super) {
    __extends(UserRole, _super);
    function UserRole() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    return UserRole;
}(__WEBPACK_IMPORTED_MODULE_0__core_model_base_entity__["a" /* BaseEntity */]));

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/user.role.js.map

/***/ }),

/***/ "../../../../../src/app/security/model/user_account.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__core_model_base_entity__ = __webpack_require__("../../../../../src/app/core/model/base.entity.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserAccount; });
var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var UserAccount = (function (_super) {
    __extends(UserAccount, _super);
    function UserAccount() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    return UserAccount;
}(__WEBPACK_IMPORTED_MODULE_0__core_model_base_entity__["a" /* BaseEntity */]));

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/user_account.js.map

/***/ }),

/***/ "../../../../../src/app/security/model/user_account_change_password_resource.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserAccountChangePasswordResource; });
var UserAccountChangePasswordResource = (function () {
    function UserAccountChangePasswordResource() {
    }
    return UserAccountChangePasswordResource;
}());

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/user_account_change_password_resource.js.map

/***/ }),

/***/ "../../../../../src/app/security/model/user_account_user_group.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__core_model_base_entity__ = __webpack_require__("../../../../../src/app/core/model/base.entity.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserAccountUserGroup; });
var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var UserAccountUserGroup = (function (_super) {
    __extends(UserAccountUserGroup, _super);
    function UserAccountUserGroup() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    return UserAccountUserGroup;
}(__WEBPACK_IMPORTED_MODULE_0__core_model_base_entity__["a" /* BaseEntity */]));

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/user_account_user_group.js.map

/***/ }),

/***/ "../../../../../src/app/security/model/user_group.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__core_model_base_entity__ = __webpack_require__("../../../../../src/app/core/model/base.entity.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserGroup; });
var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var UserGroup = (function (_super) {
    __extends(UserGroup, _super);
    function UserGroup() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    return UserGroup;
}(__WEBPACK_IMPORTED_MODULE_0__core_model_base_entity__["a" /* BaseEntity */]));

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/user_group.js.map

/***/ }),

/***/ "../../../../../src/app/security/model/user_group_searchcriteria.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserGroupSearchCriteria; });
var UserGroupSearchCriteria = (function () {
    function UserGroupSearchCriteria() {
    }
    return UserGroupSearchCriteria;
}());

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/user_group_searchcriteria.js.map

/***/ }),

/***/ "../../../../../src/app/security/model/user_group_user_role.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__core_model_base_entity__ = __webpack_require__("../../../../../src/app/core/model/base.entity.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserGroupUserRole; });
var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();

var UserGroupUserRole = (function (_super) {
    __extends(UserGroupUserRole, _super);
    function UserGroupUserRole() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    return UserGroupUserRole;
}(__WEBPACK_IMPORTED_MODULE_0__core_model_base_entity__["a" /* BaseEntity */]));

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/user_group_user_role.js.map

/***/ }),

/***/ "../../../../../src/app/security/registration/index.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__registration_component__ = __webpack_require__("../../../../../src/app/security/registration/registration.component.ts");
/* harmony namespace reexport (by used) */ __webpack_require__.d(__webpack_exports__, "a", function() { return __WEBPACK_IMPORTED_MODULE_0__registration_component__["a"]; });

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/index.js.map

/***/ }),

/***/ "../../../../../src/app/security/registration/registration.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../node_modules/css-loader/lib/css-base.js")(false);
// imports
exports.push([module.i, "@import url(https://fonts.googleapis.com/css?family=Montserrat);", ""]);

// module
exports.push([module.i, ".centered {\r\n  position: fixed;\r\n  top: 50%;\r\n  left: 50%;\r\n  -webkit-transform: translate(-50%, -50%);\r\n          transform: translate(-50%, -50%);\r\n}\r\n\r\n#registration-area{\r\n  padding-top: 50px;\r\n  font-family: 'Montserrat', sans-serif;\r\n}\r\n\r\n#small{\r\n  font-size: 14px;\r\n  color: #337ab7;\r\n    letter-spacing: 0.4px;\r\n}\r\n\r\n#registration-area input[type = text], input[type = password]{\r\n  height: 40px;\r\n  font-size: 15px;\r\n}\r\n\r\na:hover{\r\n  cursor: pointer;\r\n}\r\n\r\n.signUp-bt{\r\n  padding-top: 30px;\r\n  padding-bottom: 30px;\r\n}\r\n\r\n#sign-up{\r\n   width: 500px;\r\n    height: 40px;\r\n    font-size: large;\r\n    font-family: 'Montserrat', sans-serif;\r\n    background-color: #00aeee;\r\n}\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/security/registration/registration.component.html":
/***/ (function(module, exports) {

module.exports = "<!--<small appCompare>hi</small>-->\r\n<!--<script>\r\n  $('.js-show-password-button').on('click', function () {\r\n    var $this = $(this);\r\n    var $input = $($this.data('target'));\r\n\r\n    var newType = $input.attr('type') === 'password' ? 'text' : 'password';\r\n\r\n    $input.attr('type', newType);\r\n    $this.text(newType === 'password' ? 'show' : 'hide');\r\n  });\r\n\r\n</script> -->\r\n\r\n<p-growl [value]=\"msgs\"></p-growl>\r\n<div class=\"col-md-12\">\r\n  <div class=\"row\">\r\n    <div class=\"col-md-8 col-md-offset-1\" id=\"registration-area\">\r\n      <form class=\"form-horizontal\" role=\"form\" (ngSubmit)=\"onSubmit()\" autocomplete=\"off\" novalidate #registerForm=\"ngForm\">\r\n        <fieldset>\r\n          <legend>\r\n            <h2>Sign Up Today for FREE</h2>\r\n            <p style=\"font-size:14px\">Create an account for free and start campaigning to your customers, for a better business. <br/> Already have\r\n              an account? <a (click)=applogin() class=\"link\">Log in here</a></p>\r\n            <small id=\"small\">Please register using valid details to use Contact Swing</small>\r\n          </legend>\r\n\r\n          <!-- Name Group-->\r\n          <div class=\"form-group\">\r\n            <div class=\"col-sm-4\">\r\n              <input type=\"text\" name=\"firstName\" placeholder=\"First Name\" class=\"form-control\" minlength=\"5\" maxlength=\"50\" required [(ngModel)]=\"user.firstName\" #firstName=\"ngModel\">\r\n              <div *ngIf=\"firstName.errors && (firstName.dirty || firstName.touched)\" class=\"alert alert-danger\">\r\n                <div [hidden]=\"!firstName.errors.required\">\r\n                  First name is required\r\n                </div>\r\n                <div [hidden]=\"!firstName.errors.maxlength\">\r\n                  Max Character Lenght is 50\r\n                </div>\r\n                <div [hidden]=\"!firstName.dirty || firstName.errors.required\">\r\n                  Invalid first name\r\n                </div>\r\n              </div>\r\n            </div>\r\n            <div class=\"col-sm-2\">\r\n              <input type=\"text\" name=\"middleName\" placeholder=\"Middle Name\" class=\"form-control\" [(ngModel)]=\"user.middleName\">\r\n            </div>\r\n            <div class=\"col-sm-4\">\r\n              <input type=\"text\" name=\"lastName\" placeholder=\"Last Name\" class=\"form-control\" minlength=\"5\" required [(ngModel)]=\"user.lastName\" #lastName=\"ngModel\">\r\n            </div>\r\n          </div>\r\n\r\n          <!-- Email ID-->\r\n          <div class=\"form-group\">\r\n            <div class=\"col-sm-10\">\r\n              <input type=\"email\" placeholder=\"Email Address\" class=\"form-control\" emailPattern required [(ngModel)]=\"user.email\" name=\"email\"\r\n                #email=\"ngModel\" required pattern=\"^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$\">\r\n            </div>\r\n          </div>\r\n\r\n          <!-- Username-->\r\n          <div class=\"form-group\">\r\n            <div class=\"col-sm-10\">\r\n              <input type=\"text\" placeholder=\"Username\" class=\"form-control\" name=\"username\" minlength=\"5\" required [(ngModel)]=\"user.username\"\r\n                #username=\"ngModel\">\r\n            </div>\r\n          </div>\r\n\r\n          <!-- Password -->\r\n          <div class=\"form-group\">\r\n            <div class=\"col-sm-10\">\r\n              <input type=\"password\" placeholder=\"Password\" class=\"form-control\" compareControl=\"confirmPassword\" sourceCompare=\"true\"\r\n                required [(ngModel)]=\"user.password\" #password=\"ngModel\" name=\"password\">\r\n            </div>\r\n          </div>\r\n          <div class=\"form-group\">\r\n            <div class=\"col-sm-10\">\r\n              <input type=\"password\" placeholder=\"Retype-Password\" class=\"form-control\" [(ngModel)]=\"user.confirmPassword\" #confirmPassword=\"ngModel\"\r\n                compareControl=\"password\" name=\"confirmPassword\">\r\n            </div>\r\n          </div>\r\n\r\n          <!-- Address Section -->\r\n          <!-- Form Name -->\r\n          <legend style=\"padding-top:30px;\">Organization Details</legend>\r\n          <!-- Text input-->\r\n          <div class=\"form-group\">\r\n            <div class=\"col-sm-10\">\r\n              <input type=\"text\" name=\"companyName\" placeholder=\"Organization Name\" class=\"form-control\" required [(ngModel)]=\"user.companyName\"\r\n                name=\"companyname\" #companyname=\"ngModel\">\r\n            </div>\r\n          </div>\r\n          <div class=\"form-group\">\r\n            <div class=\"col-sm-10\">\r\n              <input type=\"text\" name=\"phonenumber\" placeholder=\"Phone Number \" class=\"form-control\" maxlength=\"10\" required [(ngModel)]=\"user.phone\"\r\n                name=\"phone\" #phone=\"ngModel\">\r\n            </div>\r\n          </div>\r\n\r\n          <!-- Text input-->\r\n          <div class=\"form-group\">\r\n            <div class=\"col-sm-10\">\r\n              <input type=\"text\" name=\"addressLine1\" placeholder=\"Address Line \" class=\"form-control\" maxlength=\"200\" required [(ngModel)]=\"user.addressLine1\"\r\n                name=\"address\" #adddress=\"ngModel\">\r\n            </div>\r\n          </div>\r\n\r\n          <!-- Text input-->\r\n          <div class=\"form-group\">\r\n            <div class=\"col-sm-10\">\r\n              <input type=\"text\" name=\"addressLine2\" placeholder=\"Suite/ Apt \" class=\"form-control\" [(ngModel)]=\"user.addressLine2\" maxlength=\"30\">\r\n            </div>\r\n          </div>\r\n\r\n          <!-- Text input-->\r\n          <div class=\"form-group\">\r\n            <div class=\"col-sm-4\">\r\n              <select class=\"form-control\" required style=\"height:40px;color: #8f8f8f;\" [(ngModel)]=\"user.country\" name=\"country\" (ngModelChange)=\"getStates($event)\">\r\n                  <option value=\"\" disabled selected>Select Country</option>\r\n                  <option *ngFor=\"let country of countries\"  value=\"{{country.fullName}}\">\r\n                    {{country.fullName}}\r\n                  </option>\r\n                </select>\r\n\r\n            </div>\r\n            <div class=\"col-sm-2\">\r\n              <select class=\"form-control\" required style=\"height:40px; color:#8f8f8f;\" [(ngModel)]=\"user.state\" name=\"state\" (ngModelChange)=\"getCities($event)\">\r\n                    <option value=\"\" disabled selected>Select State</option>\r\n                    <option *ngFor=\"let state of states\"  value=\"{{state.name}}\">\r\n                      {{state.name}}\r\n                    </option>\r\n                  </select>\r\n            </div>\r\n            <div class=\"col-sm-4\">\r\n              <select class=\"form-control\" required style=\"height:40px; color:#8f8f8f\" [(ngModel)]=\"user.city\" name=\"city\">\r\n                   <option value=\"\" disabled selected>Select City</option>\r\n                  <option *ngFor=\"let city of cities\"  value=\"{{city.name}}\">\r\n                    {{city.name}}\r\n                  </option>\r\n                </select>\r\n\r\n            </div>\r\n          </div>\r\n          <div class=\"form-group\">\r\n            <div class=\"col-sm-4\">\r\n              <input type=\"text\" name=\"postalCode\" placeholder=\"Post Code\" class=\"form-control\" maxlength=\"10\" required [(ngModel)]=\"user.zipcode\"\r\n                name=\"zipcode\" #zipcode=\"ngModel\">\r\n            </div>\r\n          </div>\r\n\r\n          <div class=\"form-check\" style=\"padding-top:20px;\">\r\n            <p>By clicking Sign Up button, you agree to Contact Swing's <a>Anti-spam Policy & Terms of Use.</a></p>\r\n\r\n          </div>\r\n          <div class=\"signUp-bt\">\r\n            <div class=\"form-group\">\r\n              <div class=\"col-sm-10\">\r\n                <button type=\"submit\" class=\"btn btn-primary\" id=\"sign-up\" [disabled]=\"!registerForm.form.valid || disabled\">Sign Up</button>\r\n              </div>\r\n            </div>\r\n          </div>\r\n\r\n          <div class=\"form-check\" style=\"padding-bottom:30px; color:#8f8f8f\">\r\n            <p>&copy;2017 All Rights Reserved. Contact Swing is a registered trademark of <br/><a (click)=\"bluespace()\">Blue Space Technologies, INC.</a>              -- <a>Privacy</a> and <a>Terms</a></p>\r\n          </div>\r\n        </fieldset>\r\n      </form>\r\n    </div>\r\n    <!-- /.col-lg-12 -->\r\n  </div>\r\n  <!-- /.row -->\r\n\r\n</div>\r\n\r\n\r\n\r\n\r\n"

/***/ }),

/***/ "../../../../../src/app/security/registration/registration.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__registration_service__ = __webpack_require__("../../../../../src/app/security/registration/registration.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_router__ = __webpack_require__("../../../router/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__model_item__ = __webpack_require__("../../../../../src/app/security/model/item.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return RegistrationComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var RegistrationComponent = (function () {
    function RegistrationComponent(router, registerService) {
        this.router = router;
        this.registerService = registerService;
        this.submitted = false;
        this.msgs = [];
        this.registered = false;
        this.disabled = false;
        this.countries = [];
        this.countryItems = [];
        this.states = [];
        this.cities = [];
        this.value = {};
        this._disabledV = '0';
        this.country_dropdown_disabled = false;
        console.log("in construct..");
        this.getCountries();
    }
    RegistrationComponent.prototype.ngOnInit = function () {
        console.log("in init");
        this.user = {
            firstName: '',
            middleName: '',
            lastName: '',
            username: '',
            password: '',
            confirmPassword: '',
            email: '',
            companyName: '',
            phone: '',
            addressLine1: '',
            addressLine2: '',
            country: '',
            state: '',
            city: '',
            zipcode: '',
            federalId: ''
        };
    };
    RegistrationComponent.prototype.clearForm = function () {
        //console.log("in clear form");
        this.user = {
            firstName: '',
            middleName: '',
            lastName: '',
            username: '',
            password: '',
            confirmPassword: '',
            email: '',
            companyName: '',
            phone: '',
            addressLine1: '',
            addressLine2: '',
            country: '',
            state: '',
            city: '',
            zipcode: '',
            federalId: ''
        };
    };
    RegistrationComponent.prototype.getCountries = function () {
        var _this = this;
        this.registerService.getCountries()
            .subscribe(function (data) {
            _this.countries = data;
            console.log("Countries loaded with " + _this.countries.length + " size");
            for (var _i = 0, _a = _this.countries; _i < _a.length; _i++) {
                var cnt = _a[_i];
                var item = new __WEBPACK_IMPORTED_MODULE_3__model_item__["a" /* Item */]();
                item.id = cnt.id;
                item.text = cnt.fullName;
                _this.countryItems.push(item);
            }
            console.log("countries populated with size : " + _this.countryItems.length);
        }, function (error) {
            console.error("Failed to get country list");
        });
    };
    RegistrationComponent.prototype.getStates = function (fullName) {
        var _this = this;
        console.log(fullName + " passed as argument to get states ");
        this.registerService.getStates(fullName)
            .subscribe(function (data) {
            _this.states = data;
        }, function (error) {
            console.error("Failed to get state list");
        });
    };
    RegistrationComponent.prototype.getCities = function (name) {
        var _this = this;
        console.log(name + " passed as argument to get states");
        this.registerService.getCities(name)
            .subscribe(function (data) {
            _this.cities = data;
        }, function (error) {
            console.error("Failed to get city list");
        });
    };
    RegistrationComponent.prototype.onSubmit = function () {
        this.submitted = true;
        this.disabled = true;
        this.register(this.user);
    };
    RegistrationComponent.prototype.register = function (model) {
        var _this = this;
        this.registerService.register(model)
            .subscribe(function (data) {
            console.log(data);
            _this.disabled = false;
            _this.msgs.push({ severity: "info", summary: "Registration Successful", detail: "Registration request has been sent to the administrator and awaiting approval. Please check your email for details on account activation." });
            _this.registered = false;
            _this.clearForm();
            _this.router.navigate(['/appRegister']);
            //this.router.navigate(['/appLogin']);
        }, function (error) {
            _this.msgs.push({ severity: "error", summary: "Registration Failed", detail: error });
            _this.registered = false;
            _this.disabled = false;
        });
    };
    ;
    RegistrationComponent.prototype.home = function () {
        this.router.navigate(['/']);
    };
    RegistrationComponent.prototype.applogin = function () {
        this.router.navigate(['/appLogin']);
    };
    RegistrationComponent.prototype.bluespace = function () {
        window.location.href = 'http://www.bluespacetech.com';
    };
    RegistrationComponent.prototype.showDialog = function () {
        this.registered = true;
    };
    Object.defineProperty(RegistrationComponent.prototype, "disabledV", {
        get: function () {
            return this._disabledV;
        },
        set: function (value) {
            this._disabledV = value;
            this.disabled = this._disabledV === '1';
        },
        enumerable: true,
        configurable: true
    });
    RegistrationComponent.prototype.selected = function (value) {
        console.log('Selected value is: ', value);
    };
    RegistrationComponent.prototype.removed = function (value) {
        console.log('Removed value is: ', value);
    };
    RegistrationComponent.prototype.typed = function (value) {
        console.log('New search input: ', value);
    };
    RegistrationComponent.prototype.refreshValue = function (value) {
        this.value = value;
    };
    return RegistrationComponent;
}());
RegistrationComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-registration',
        template: __webpack_require__("../../../../../src/app/security/registration/registration.component.html"),
        styles: [__webpack_require__("../../../../../src/app/security/registration/registration.component.css")],
        providers: [__WEBPACK_IMPORTED_MODULE_1__registration_service__["a" /* RegistrationService */]]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_2__angular_router__["Router"] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_router__["Router"]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_1__registration_service__["a" /* RegistrationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__registration_service__["a" /* RegistrationService */]) === "function" && _b || Object])
], RegistrationComponent);

var _a, _b;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/registration.component.js.map

/***/ }),

/***/ "../../../../../src/app/security/registration/registration.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__("../../../http/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__ = __webpack_require__("../../../../rxjs/Observable.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return RegistrationService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var RegistrationService = (function () {
    function RegistrationService(http) {
        this.http = http;
    }
    RegistrationService.prototype.register = function (model) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        var requestOptions = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* RequestOptions */]({
            method: __WEBPACK_IMPORTED_MODULE_1__angular_http__["h" /* RequestMethod */].Post,
            headers: headers,
            body: JSON.stringify(model)
        });
        var body = JSON.stringify(model);
        console.log(body);
        return this.http.post('new/register', body, requestOptions).catch(this.handleError);
    };
    RegistrationService.prototype.getCountries = function () {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.get('new/getCountries')
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    RegistrationService.prototype.getStates = function (fullName) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.post('new/getStatesFromCountry/' + fullName, { headers: headers })
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    RegistrationService.prototype.getCities = function (name) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.post('new/getCitiesFromState/' + name, { headers: headers })
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    RegistrationService.prototype.handleError = function (error) {
        // In a real world app, we might use a remote logging infrastructure
        //console.log(error);
        var errMsg;
        if (error instanceof __WEBPACK_IMPORTED_MODULE_1__angular_http__["f" /* Response */]) {
            var body = error.json() || '';
            var err = body.error || JSON.stringify(body);
            errMsg = error.status + " - " + (error.statusText || '') + " " + err;
        }
        else {
            errMsg = error.message ? error.message : error.toString();
        }
        //console.error(errMsg);
        return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].throw(errMsg);
    };
    return RegistrationService;
}());
RegistrationService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */]) === "function" && _a || Object])
], RegistrationService);

var _a;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/registration.service.js.map

/***/ }),

/***/ "../../../../../src/app/security/security.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common__ = __webpack_require__("../../../common/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_forms__ = __webpack_require__("../../../forms/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_http__ = __webpack_require__("../../../http/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__angular_material__ = __webpack_require__("../../../material/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__user_role_user_role_component__ = __webpack_require__("../../../../../src/app/security/user-role/user-role.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__user_role_user_role_service__ = __webpack_require__("../../../../../src/app/security/user-role/user-role.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__user_group_user_group_component__ = __webpack_require__("../../../../../src/app/security/user-group/user-group.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__user_group_user_group_service__ = __webpack_require__("../../../../../src/app/security/user-group/user-group.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__user_account_user_account_component__ = __webpack_require__("../../../../../src/app/security/user-account/user-account.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__change_password_change_password_component__ = __webpack_require__("../../../../../src/app/security/change-password/change-password.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__account_approval_accountapproval_component__ = __webpack_require__("../../../../../src/app/security/account-approval/accountapproval.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__user_account_user_account_service__ = __webpack_require__("../../../../../src/app/security/user-account/user-account.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__login_login_component__ = __webpack_require__("../../../../../src/app/security/login/login.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14__registration_registration_component__ = __webpack_require__("../../../../../src/app/security/registration/registration.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15__login_login_service__ = __webpack_require__("../../../../../src/app/security/login/login.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_16__registration_registration_service__ = __webpack_require__("../../../../../src/app/security/registration/registration.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_17_ng2_select__ = __webpack_require__("../../../../ng2-select/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_17_ng2_select___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_17_ng2_select__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_18__account_approval_accountapproval_service__ = __webpack_require__("../../../../../src/app/security/account-approval/accountapproval.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_19_primeng_primeng__ = __webpack_require__("../../../../primeng/primeng.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_19_primeng_primeng___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_19_primeng_primeng__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return SecurityModule; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};




















var SecurityModule = (function () {
    function SecurityModule() {
    }
    return SecurityModule;
}());
SecurityModule = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["NgModule"])({
        declarations: [
            __WEBPACK_IMPORTED_MODULE_5__user_role_user_role_component__["a" /* UserRoleComponent */],
            __WEBPACK_IMPORTED_MODULE_9__user_account_user_account_component__["a" /* UserAccountComponent */],
            __WEBPACK_IMPORTED_MODULE_7__user_group_user_group_component__["a" /* UserGroupComponent */],
            __WEBPACK_IMPORTED_MODULE_13__login_login_component__["a" /* LoginComponent */],
            __WEBPACK_IMPORTED_MODULE_14__registration_registration_component__["a" /* RegistrationComponent */],
            __WEBPACK_IMPORTED_MODULE_11__account_approval_accountapproval_component__["a" /* AccountApprovalComponent */],
            __WEBPACK_IMPORTED_MODULE_10__change_password_change_password_component__["a" /* ChangePasswordComponent */]
        ],
        imports: [
            __WEBPACK_IMPORTED_MODULE_1__angular_common__["CommonModule"],
            __WEBPACK_IMPORTED_MODULE_2__angular_forms__["FormsModule"], __WEBPACK_IMPORTED_MODULE_3__angular_http__["a" /* HttpModule */], __WEBPACK_IMPORTED_MODULE_3__angular_http__["b" /* JsonpModule */],
            __WEBPACK_IMPORTED_MODULE_4__angular_material__["a" /* MaterialModule */].forRoot(),
            __WEBPACK_IMPORTED_MODULE_17_ng2_select__["SelectModule"],
            __WEBPACK_IMPORTED_MODULE_19_primeng_primeng__["DataTableModule"], __WEBPACK_IMPORTED_MODULE_19_primeng_primeng__["ButtonModule"], __WEBPACK_IMPORTED_MODULE_19_primeng_primeng__["PickListModule"],
            __WEBPACK_IMPORTED_MODULE_19_primeng_primeng__["DialogModule"], __WEBPACK_IMPORTED_MODULE_19_primeng_primeng__["PanelModule"], __WEBPACK_IMPORTED_MODULE_19_primeng_primeng__["SharedModule"], __WEBPACK_IMPORTED_MODULE_19_primeng_primeng__["GrowlModule"], __WEBPACK_IMPORTED_MODULE_19_primeng_primeng__["MultiSelectModule"], __WEBPACK_IMPORTED_MODULE_19_primeng_primeng__["ListboxModule"], __WEBPACK_IMPORTED_MODULE_19_primeng_primeng__["TabViewModule"], __WEBPACK_IMPORTED_MODULE_19_primeng_primeng__["DropdownModule"]
        ],
        providers: [__WEBPACK_IMPORTED_MODULE_6__user_role_user_role_service__["a" /* UserRoleService */], __WEBPACK_IMPORTED_MODULE_15__login_login_service__["a" /* LoginService */], __WEBPACK_IMPORTED_MODULE_16__registration_registration_service__["a" /* RegistrationService */], __WEBPACK_IMPORTED_MODULE_8__user_group_user_group_service__["a" /* UserGroupService */], __WEBPACK_IMPORTED_MODULE_12__user_account_user_account_service__["a" /* UserAccountService */], __WEBPACK_IMPORTED_MODULE_18__account_approval_accountapproval_service__["a" /* AccountApprovalService */]],
    })
], SecurityModule);

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/security.module.js.map

/***/ }),

/***/ "../../../../../src/app/security/user-account/index.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__user_account_component__ = __webpack_require__("../../../../../src/app/security/user-account/user-account.component.ts");
/* harmony namespace reexport (by used) */ __webpack_require__.d(__webpack_exports__, "a", function() { return __WEBPACK_IMPORTED_MODULE_0__user_account_component__["a"]; });

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/index.js.map

/***/ }),

/***/ "../../../../../src/app/security/user-account/user-account.component.html":
/***/ (function(module, exports) {

module.exports = "<p-growl [value]=\"msgs\"></p-growl>\r\n<div class=\"main-panel\" style=\"min-height:110vh;\">\r\n<div *ngIf=\"!displayUserAccountDetails\">\r\n  <md-card>\r\n    <md-card-title color=\"primary\">Search User Accounts</md-card-title>\r\n    <md-card-content>\r\n      <md-input placeholder=\"Name\" [(ngModel)]=\"userAccountSearchCriteria.username\" class=\"col-md-5\"></md-input>\r\n      <div class=\"col-md-2\"></div>\r\n      <md-input placeholder=\"Email\" [(ngModel)]=\"userAccountSearchCriteria.email\" class=\"col-md-5\"></md-input>\r\n    </md-card-content>\r\n    <md-card-actions class=\"center\">\r\n      <button md-raised-button color=\"primary\" (click)=\"searchUserAccounts()\" *ngIf=\"authorizationService.isUserHasRole('UI_USERS_ACCESS')\">Search</button>\r\n      <button md-raised-button color=\"primary\" (click)=\"resetSearchCriteria()\">Reset</button>\r\n    </md-card-actions>\r\n  </md-card>\r\n  <br>\r\n  <br>\r\n  <p-dataTable [value]=\"userAccounts\" selectionMode=\"single\" [responsive]=\"true\" (onRowSelect)=\"onRowSelect($event)\">\r\n    <header>User Accounts</header>\r\n    <p-column field=\"username\" header=\"Name\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n    <p-column field=\"email\" header=\"Email\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n    <p-footer>\r\n      <div class=\"row\">\r\n        <div class=\"col-md-12\">\r\n          <button md-raised-button color=\"primary\" (click)=\"createUserAccount()\" *ngIf=\"authorizationService.isUserHasRole('UI_USERS_CREATE')\">Create</button>\r\n          <!--button md-raised-button color=\"primary\">Import</button-->\r\n        </div>\r\n      </div>\r\n    </p-footer>\r\n  </p-dataTable>\r\n</div>\r\n<div *ngIf=\"displayUserAccountDetails\">\r\n  <md-card>\r\n    <md-card-title color=\"primary\">Create User Account</md-card-title>\r\n    <md-card-content>\r\n      <div>\r\n        <md-input placeholder=\"Name\" [(ngModel)]=\"selectedUserAccount.username\" class=\"col-md-3\"></md-input>\r\n          <div class=\"col-md-1\"></div>\r\n        <md-input placeholder=\"Company Name\" [(ngModel)]=\"selectedUserAccount.companyName\" class=\"col-md-3\"></md-input>  \r\n\t\t      <div class=\"col-md-1\"></div>\r\n        <md-input placeholder=\"Email\" [(ngModel)]=\"selectedUserAccount.email\" class=\"col-md-4\"></md-input> \r\n      </div>\r\n      <div style=\"margin-top:15px;\">\r\n        <md-checkbox [(ngModel)]=\"selectedUserAccount.active\" class=\"col-md-5\" >Active</md-checkbox>\r\n        <div class=\"col-md-2\"></div>\r\n        <md-checkbox [(ngModel)]=\"selectedUserAccount.accountExpired\" class=\"col-md-5\" >Account Expired</md-checkbox>\r\n      </div>\r\n      <div>\r\n        <md-checkbox [(ngModel)]=\"selectedUserAccount.credentialsExpired\" class=\"col-md-5\" >Credentials Expired</md-checkbox>\r\n        <div class=\"col-md-2\"></div>\r\n        <md-checkbox [(ngModel)]=\"selectedUserAccount.accountLocked\" class=\"col-md-5\">Account Locked</md-checkbox>\r\n      </div>\r\n      <br>\r\n      <br>\r\n      <br>\r\n      <br>\r\n      <div>\r\n        <p-pickList [source]=\"sourceUserAccountUserGroups\" [target]=\"targetUserAccountUserGroups\" sourceHeader=\"Available Groups\" targetHeader=\"Selected Groups\"\r\n          [responsive]=\"true\" [sourceStyle]=\"{'height':'300px'}\" [targetStyle]=\"{'height':'300px'}\">\r\n          <template let-userAccountUserRole pTemplate=\"item\">\r\n            <div class=\"ui-helper-clearfix\">\r\n              <div style=\"font-size:14px;float:center;margin:15px 5px 0 0\">{{userAccountUserRole.userGroup.groupName}}</div>\r\n            </div>\r\n          </template>\r\n        </p-pickList>\r\n      </div>\r\n    </md-card-content>\r\n    <md-card-actions class=\"center\">\r\n      <button md-raised-button color=\"primary\" (click)=\"submitNewUserAccount()\" *ngIf=\"authorizationService.isUserHasRole('UI_USERS_UPDATE')\">Submit</button>\r\n      <button md-raised-button color=\"primary\" (click)=\"deleteNewUserAccount()\" *ngIf=\"authorizationService.isUserHasRole('UI_USERS_DELETE')\">Delete</button>\r\n      <button md-raised-button color=\"primary\" (click)=\"backToUserAccounts()\">Back</button>\r\n    </md-card-actions>\r\n  </md-card>\r\n</div>\r\n\r\n</div>"

/***/ }),

/***/ "../../../../../src/app/security/user-account/user-account.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__user_group_user_group_service__ = __webpack_require__("../../../../../src/app/security/user-group/user-group.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__model_user_group_searchcriteria__ = __webpack_require__("../../../../../src/app/security/model/user_group_searchcriteria.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__model_UserAccountSearchCriteria__ = __webpack_require__("../../../../../src/app/security/model/UserAccountSearchCriteria.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__model_user_account__ = __webpack_require__("../../../../../src/app/security/model/user_account.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__user_account_user_account_service__ = __webpack_require__("../../../../../src/app/security/user-account/user-account.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__model_user_account_user_group__ = __webpack_require__("../../../../../src/app/security/model/user_account_user_group.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__core_authorization_service__ = __webpack_require__("../../../../../src/app/core/authorization.service.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserAccountComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};








var UserAccountComponent = (function () {
    function UserAccountComponent(userAccountService, userGroupService, authorizationService) {
        this.userAccountService = userAccountService;
        this.userGroupService = userGroupService;
        this.authorizationService = authorizationService;
        this.sourceUserAccountUserGroups = [];
        this.targetUserAccountUserGroups = [];
        this.availableUserGroups = [];
        this.userGroupUserRoles = [];
        this.userGroupSearchCriteria = new __WEBPACK_IMPORTED_MODULE_2__model_user_group_searchcriteria__["a" /* UserGroupSearchCriteria */]();
        this.userAccountSearchCriteria = new __WEBPACK_IMPORTED_MODULE_3__model_UserAccountSearchCriteria__["a" /* UserAccountSearchCriteria */]();
        this.msgs = [];
        this.companylist = [];
    }
    UserAccountComponent.prototype.ngOnInit = function () {
    };
    UserAccountComponent.prototype.onRowSelect = function (event) {
        var _this = this;
        this.selectedUserAccount = event.data;
        this.targetUserAccountUserGroups = [];
        this.availableUserGroups = [];
        this.userAccountService.getUserAccountById(this.selectedUserAccount.objectId)
            .subscribe(function (userAccountFromDB) {
            _this.selectedUserAccount = userAccountFromDB;
            _this.targetUserAccountUserGroups = userAccountFromDB.userAccountUserGroups;
            for (var i = 0; i < userAccountFromDB.userAccountUserGroups.length; i++) {
                _this.availableUserGroups[i] = userAccountFromDB.userAccountUserGroups[i].userGroup.groupName;
            }
            _this.displayUserAccountDetails = true;
            _this.updateUserAccount = true;
            _this.searchUserGroups();
        });
    };
    UserAccountComponent.prototype.searchUserAccounts = function () {
        var _this = this;
        this.userAccountService.getUserAccounts(this.userAccountSearchCriteria)
            .subscribe(function (userAccounts) {
            _this.userAccounts = userAccounts;
        }, function (error) {
        });
    };
    ;
    UserAccountComponent.prototype.getCompanyList = function () {
        var _this = this;
        this.userAccountService.getCompanyList()
            .subscribe(function (data) {
            _this.companylist = data;
        });
    };
    UserAccountComponent.prototype.searchUserGroups = function () {
        var _this = this;
        this.sourceUserAccountUserGroups = [];
        this.userGroupService.getUserGroups(this.userGroupSearchCriteria)
            .subscribe(function (userGroups) {
            var count = 0;
            for (var i = 0; i < userGroups.length; i++) {
                if (_this.availableUserGroups.indexOf(userGroups[i].groupName) < 0) {
                    var userAccountUserGroup = new __WEBPACK_IMPORTED_MODULE_6__model_user_account_user_group__["a" /* UserAccountUserGroup */]();
                    userAccountUserGroup.userGroup = userGroups[i];
                    _this.sourceUserAccountUserGroups[count] = userAccountUserGroup;
                    count++;
                }
            }
        }, function (error) {
        });
    };
    ;
    UserAccountComponent.prototype.resetSearchCriteria = function () {
        this.userGroupSearchCriteria = new __WEBPACK_IMPORTED_MODULE_2__model_user_group_searchcriteria__["a" /* UserGroupSearchCriteria */]();
    };
    ;
    UserAccountComponent.prototype.createUserAccount = function () {
        this.selectedUserAccount = new __WEBPACK_IMPORTED_MODULE_4__model_user_account__["a" /* UserAccount */]();
        this.targetUserAccountUserGroups = [];
        this.displayUserAccountDetails = true;
        this.updateUserAccount = false;
        this.searchUserGroups();
    };
    ;
    UserAccountComponent.prototype.backToUserAccounts = function () {
        this.selectedUserAccount = new __WEBPACK_IMPORTED_MODULE_4__model_user_account__["a" /* UserAccount */]();
        this.displayUserAccountDetails = false;
        this.updateUserAccount = false;
    };
    ;
    UserAccountComponent.prototype.submitNewUserAccount = function () {
        var _this = this;
        this.msgs = [];
        this.displayUserAccountDetails = false;
        this.selectedUserAccount.userAccountUserGroups = this.targetUserAccountUserGroups;
        if (this.updateUserAccount) {
            this.userAccountService.updateUserAccount(this.selectedUserAccount)
                .subscribe(function () {
                _this.searchUserAccounts();
                _this.msgs.push({ severity: "info", summary: "User account updated successfully.", detail: "" });
            }, function (error) {
                _this.msgs.push({ severity: "error", summary: "User account updation failed.", detail: error });
            });
        }
        else {
            this.userAccountService.createUserAccount(this.selectedUserAccount)
                .subscribe(function () {
                _this.searchUserAccounts();
                _this.msgs.push({ severity: "info", summary: "User account created successfully.", detail: "" });
            }, function (error) {
                _this.msgs.push({ severity: "error", summary: "User account creation failed.", detail: error });
            });
        }
    };
    ;
    UserAccountComponent.prototype.deleteNewUserAccount = function () {
        var _this = this;
        this.msgs = [];
        this.displayUserAccountDetails = false;
        this.userAccountService.deleteUserAccountById(this.selectedUserAccount.objectId)
            .subscribe(function () {
            _this.searchUserAccounts();
            _this.msgs.push({ severity: "info", summary: "User account deleted successfully.", detail: "" });
        }, function (error) {
            _this.msgs.push({ severity: "error", summary: "User account deletion failed.", detail: error });
        });
    };
    ;
    return UserAccountComponent;
}());
UserAccountComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'user-account',
        template: __webpack_require__("../../../../../src/app/security/user-account/user-account.component.html")
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_5__user_account_user_account_service__["a" /* UserAccountService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_5__user_account_user_account_service__["a" /* UserAccountService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_1__user_group_user_group_service__["a" /* UserGroupService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__user_group_user_group_service__["a" /* UserGroupService */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_7__core_authorization_service__["a" /* AuthorizationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_7__core_authorization_service__["a" /* AuthorizationService */]) === "function" && _c || Object])
], UserAccountComponent);

var _a, _b, _c;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/user-account.component.js.map

/***/ }),

/***/ "../../../../../src/app/security/user-account/user-account.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__("../../../http/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__ = __webpack_require__("../../../../rxjs/Observable.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserAccountService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var UserAccountService = (function () {
    function UserAccountService(http) {
        this.http = http;
    }
    UserAccountService.prototype.getUserAccounts = function (userAccountSearchCriteria) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.post('userAccounts/search', JSON.stringify(userAccountSearchCriteria), { headers: headers })
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    UserAccountService.prototype.getCompanyList = function () {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.get('userAccounts/getCompanyList', { headers: headers })
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    UserAccountService.prototype.getUserAccountById = function (id) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.get('userAccounts/' + id)
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    UserAccountService.prototype.createUserAccount = function (userAccount) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.post('userAccounts', JSON.stringify(userAccount), { headers: headers })
            .map(function (res) { return; })
            .catch(this.handleError);
    };
    UserAccountService.prototype.deleteUserAccountById = function (id) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.delete('userAccounts/' + id)
            .map(function (res) { return; })
            .catch(this.handleError);
    };
    UserAccountService.prototype.updateUserAccount = function (userAccount) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.put('userAccounts' + "/" + userAccount.objectId, JSON.stringify(userAccount), { headers: headers })
            .map(function (res) { return; })
            .catch(this.handleError);
    };
    UserAccountService.prototype.changeUserPassword = function (userAccountChangePasswordResource) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.post('userAccounts/changepassword', JSON.stringify(userAccountChangePasswordResource), { headers: headers })
            .map(function (res) { return; })
            .catch(this.handleError);
    };
    UserAccountService.prototype.extractData = function (res) {
        var body = res.json();
        return body.data || {};
    };
    UserAccountService.prototype.handleError = function (error) {
        // In a real world app, we might use a remote logging infrastructure
        //console.log(error);
        var errMsg;
        if (error instanceof __WEBPACK_IMPORTED_MODULE_1__angular_http__["f" /* Response */]) {
            var body = error.json() || '';
            var err = body.error || JSON.stringify(body);
            errMsg = error.status + " - " + (error.statusText || '') + " " + err;
        }
        else {
            errMsg = error.message ? error.message : error.toString();
        }
        //console.error(errMsg);
        return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].throw(errMsg);
    };
    return UserAccountService;
}());
UserAccountService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */]) === "function" && _a || Object])
], UserAccountService);

var _a;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/user-account.service.js.map

/***/ }),

/***/ "../../../../../src/app/security/user-group/index.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__user_group_component__ = __webpack_require__("../../../../../src/app/security/user-group/user-group.component.ts");
/* harmony namespace reexport (by used) */ __webpack_require__.d(__webpack_exports__, "a", function() { return __WEBPACK_IMPORTED_MODULE_0__user_group_component__["a"]; });

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/index.js.map

/***/ }),

/***/ "../../../../../src/app/security/user-group/user-group.component.html":
/***/ (function(module, exports) {

module.exports = "<p-growl [value]=\"msgs\"></p-growl>\r\n<div class=\"main-panel\" style=\"min-height:110vh;\">\r\n<div *ngIf=\"!displayUserGroupDetails\">\r\n  <md-card>\r\n    <md-card-title color=\"primary\">Search User Groups</md-card-title>\r\n    <md-card-content>\r\n      <md-input placeholder=\"Name\" [(ngModel)]=\"userGroupSearchCriteria.groupName\" class=\"col-md-5\"></md-input>\r\n      <div class=\"col-md-2\"></div>\r\n      <md-input placeholder=\"Description\" [(ngModel)]=\"userGroupSearchCriteria.description\" class=\"col-md-5\"></md-input>\r\n    </md-card-content>\r\n    <md-card-actions class=\"center\">\r\n      <button md-raised-button color=\"primary\" (click)=\"searchUserGroups()\" *ngIf=\"authorizationService.isUserHasRole('UI_USER_GROUPS_ACCESS')\">Search</button>\r\n      <button md-raised-button color=\"primary\" (click)=\"resetSearchCriteria()\">Reset</button>\r\n    </md-card-actions>\r\n  </md-card>\r\n  <br>\r\n  <br>\r\n  <p-dataTable [value]=\"userGroups\" selectionMode=\"single\" [responsive]=\"true\" (onRowSelect)=\"onRowSelect($event)\">\r\n    <header>User Groups</header>\r\n    <p-column field=\"groupName\" header=\"Name\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n    <p-column field=\"description\" header=\"Description\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n    <p-footer>\r\n      <div class=\"row\">\r\n        <div class=\"col-md-12\">\r\n          <button md-raised-button color=\"primary\" (click)=\"createUserGroup()\" *ngIf=\"authorizationService.isUserHasRole('UI_USER_GROUPS_CREATE')\">Create</button>\r\n          <!--button md-raised-button color=\"primary\">Import</button-->\r\n        </div>\r\n      </div>\r\n    </p-footer>\r\n  </p-dataTable>\r\n</div>\r\n<div *ngIf=\"displayUserGroupDetails\">\r\n  <md-card>\r\n    <md-card-title color=\"primary\">Create User Group</md-card-title>\r\n    <md-card-content>\r\n      <div>\r\n        <md-input placeholder=\"Name\" [(ngModel)]=\"selectedUserGroup.groupName\" class=\"col-md-5\"></md-input>\r\n        <div class=\"col-md-2\"></div>\r\n        <md-input placeholder=\"Description\" [(ngModel)]=\"selectedUserGroup.description\" class=\"col-md-5\"></md-input>\r\n      </div>\r\n      <br>\r\n      <br>\r\n      <div>\r\n        <p-pickList [source]=\"sourceUserGroupUserRoles\" [target]=\"targetUserGroupUserRoles\" sourceHeader=\"Available Roles\" targetHeader=\"Selected Roles\"\r\n          [responsive]=\"true\" [sourceStyle]=\"{'height':'300px'}\" [targetStyle]=\"{'height':'300px'}\">\r\n          <template let-userGroupUserRole pTemplate=\"item\">\r\n            <div class=\"ui-helper-clearfix\">\r\n              <div style=\"font-size:14px;float:center;margin:15px 5px 0 0\">{{userGroupUserRole.userRole.roleName}}</div>\r\n            </div>\r\n          </template>\r\n        </p-pickList>\r\n      </div>\r\n    </md-card-content>\r\n    <md-card-actions class=\"center\">\r\n      <button md-raised-button color=\"primary\" (click)=\"submitNewUserGroup()\" *ngIf=\"authorizationService.isUserHasRole('UI_USER_GROUPS_UPDATE')\">Submit</button>\r\n      <button md-raised-button color=\"primary\" (click)=\"deleteNewUserGroup()\" *ngIf=\"authorizationService.isUserHasRole('UI_USER_GROUPS_DELETE')\">Delete</button>\r\n      <button md-raised-button color=\"primary\" (click)=\"backToUserGroups()\">Back</button>\r\n    </md-card-actions>\r\n  </md-card>\r\n</div>\r\n</div>"

/***/ }),

/***/ "../../../../../src/app/security/user-group/user-group.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__user_group_service__ = __webpack_require__("../../../../../src/app/security/user-group/user-group.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__user_role_user_role_service__ = __webpack_require__("../../../../../src/app/security/user-role/user-role.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__model_user_group__ = __webpack_require__("../../../../../src/app/security/model/user_group.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__model_user_group_user_role__ = __webpack_require__("../../../../../src/app/security/model/user_group_user_role.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__model_user_group_searchcriteria__ = __webpack_require__("../../../../../src/app/security/model/user_group_searchcriteria.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__model_user_role_search_criteria__ = __webpack_require__("../../../../../src/app/security/model/user.role.search.criteria.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__core_authorization_service__ = __webpack_require__("../../../../../src/app/core/authorization.service.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserGroupComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};








var UserGroupComponent = (function () {
    function UserGroupComponent(userGroupService, userRoleService, authorizationService) {
        this.userGroupService = userGroupService;
        this.userRoleService = userRoleService;
        this.authorizationService = authorizationService;
        this.sourceUserGroupUserRoles = [];
        this.targetUserGroupUserRoles = [];
        this.availableUserRoles = [];
        this.userGroupUserRoles = [];
        this.userGroupSearchCriteria = new __WEBPACK_IMPORTED_MODULE_5__model_user_group_searchcriteria__["a" /* UserGroupSearchCriteria */]();
        this.userRoleSearchCriteria = new __WEBPACK_IMPORTED_MODULE_6__model_user_role_search_criteria__["a" /* UserRoleSearchCriteria */]();
        this.msgs = [];
    }
    UserGroupComponent.prototype.ngOnInit = function () {
    };
    UserGroupComponent.prototype.onRowSelect = function (event) {
        var _this = this;
        this.selectedUserGroup = event.data;
        this.targetUserGroupUserRoles = [];
        this.availableUserRoles = [];
        this.userGroupService.getUserGroupById(this.selectedUserGroup.objectId)
            .subscribe(function (userGroupFromDB) {
            _this.selectedUserGroup = userGroupFromDB;
            _this.targetUserGroupUserRoles = userGroupFromDB.userGroupUserRoles;
            for (var i = 0; i < userGroupFromDB.userGroupUserRoles.length; i++) {
                _this.availableUserRoles[i] = userGroupFromDB.userGroupUserRoles[i].userRole.roleName;
            }
            _this.displayUserGroupDetails = true;
            _this.updateUserGroup = true;
            _this.searchUserRoles();
        });
    };
    UserGroupComponent.prototype.searchUserGroups = function () {
        var _this = this;
        this.userGroupService.getUserGroups(this.userGroupSearchCriteria)
            .subscribe(function (userGroups) {
            _this.userGroups = userGroups;
        }, function (error) {
        });
    };
    ;
    UserGroupComponent.prototype.searchUserRoles = function () {
        var _this = this;
        this.sourceUserGroupUserRoles = [];
        this.userRoleService.getUserRoles(this.userRoleSearchCriteria)
            .subscribe(function (userRoles) {
            var count = 0;
            for (var i = 0; i < userRoles.length; i++) {
                if (_this.availableUserRoles.indexOf(userRoles[i].roleName) < 0) {
                    var userGroupUserRole = new __WEBPACK_IMPORTED_MODULE_4__model_user_group_user_role__["a" /* UserGroupUserRole */]();
                    userGroupUserRole.userRole = userRoles[i];
                    _this.sourceUserGroupUserRoles[count] = userGroupUserRole;
                    count++;
                }
            }
        }, function (error) {
        });
    };
    ;
    UserGroupComponent.prototype.resetSearchCriteria = function () {
        this.userGroupSearchCriteria = new __WEBPACK_IMPORTED_MODULE_5__model_user_group_searchcriteria__["a" /* UserGroupSearchCriteria */]();
    };
    ;
    UserGroupComponent.prototype.createUserGroup = function () {
        this.selectedUserGroup = new __WEBPACK_IMPORTED_MODULE_3__model_user_group__["a" /* UserGroup */]();
        this.displayUserGroupDetails = true;
        this.updateUserGroup = false;
        this.searchUserRoles();
    };
    ;
    UserGroupComponent.prototype.backToUserGroups = function () {
        this.selectedUserGroup = new __WEBPACK_IMPORTED_MODULE_3__model_user_group__["a" /* UserGroup */]();
        this.displayUserGroupDetails = false;
        this.updateUserGroup = false;
    };
    ;
    UserGroupComponent.prototype.submitNewUserGroup = function () {
        var _this = this;
        this.msgs = [];
        this.displayUserGroupDetails = false;
        this.selectedUserGroup.userGroupUserRoles = this.targetUserGroupUserRoles;
        if (this.updateUserGroup) {
            this.userGroupService.updateUserGroup(this.selectedUserGroup)
                .subscribe(function () {
                _this.searchUserGroups();
                _this.msgs.push({ severity: "info", summary: "User group updated successfully.", detail: "" });
            }, function (error) {
                _this.msgs.push({ severity: "error", summary: "User group updation failed.", detail: error });
            });
        }
        else {
            this.userGroupService.createUserGroup(this.selectedUserGroup)
                .subscribe(function () {
                _this.searchUserGroups();
                _this.msgs.push({ severity: "info", summary: "User group created successfully.", detail: "" });
            }, function (error) {
                _this.msgs.push({ severity: "error", summary: "User group creation failed.", detail: error });
            });
        }
    };
    ;
    UserGroupComponent.prototype.deleteNewUserGroup = function () {
        var _this = this;
        this.msgs = [];
        this.displayUserGroupDetails = false;
        this.userGroupService.deleteUserGroupById(this.selectedUserGroup.objectId)
            .subscribe(function () {
            _this.searchUserGroups();
            _this.msgs.push({ severity: "info", summary: "User group deleted successfully.", detail: "" });
        }, function (error) {
            _this.msgs.push({ severity: "error", summary: "User group deletion failed.", detail: error });
        });
    };
    ;
    return UserGroupComponent;
}());
UserGroupComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'user-group',
        template: __webpack_require__("../../../../../src/app/security/user-group/user-group.component.html")
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__user_group_service__["a" /* UserGroupService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__user_group_service__["a" /* UserGroupService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__user_role_user_role_service__["a" /* UserRoleService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__user_role_user_role_service__["a" /* UserRoleService */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_7__core_authorization_service__["a" /* AuthorizationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_7__core_authorization_service__["a" /* AuthorizationService */]) === "function" && _c || Object])
], UserGroupComponent);

var _a, _b, _c;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/user-group.component.js.map

/***/ }),

/***/ "../../../../../src/app/security/user-group/user-group.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__("../../../http/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__ = __webpack_require__("../../../../rxjs/Observable.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserGroupService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var UserGroupService = (function () {
    function UserGroupService(http) {
        this.http = http;
    }
    UserGroupService.prototype.getUserGroups = function (userGroupSearchCriteria) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.post('userGroups/search', JSON.stringify(userGroupSearchCriteria), { headers: headers })
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    UserGroupService.prototype.getUserGroupById = function (id) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.get('userGroups/' + id)
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    UserGroupService.prototype.createUserGroup = function (userGroup) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        headers.append('Access-Control-Allow-Origin', '*');
        return this.http.post('userGroups', JSON.stringify(userGroup), { headers: headers })
            .map(function (res) { return; })
            .catch(this.handleError);
    };
    UserGroupService.prototype.deleteUserGroupById = function (id) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.delete('userGroups/' + id)
            .map(function (res) { return; })
            .catch(this.handleError);
    };
    UserGroupService.prototype.updateUserGroup = function (userGroup) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.put('userGroups' + "/" + userGroup.objectId, JSON.stringify(userGroup), { headers: headers })
            .map(function (res) { return; })
            .catch(this.handleError);
    };
    UserGroupService.prototype.extractData = function (res) {
        var body = res.json();
        console.log('body extractData ', res);
        return body.data || {};
    };
    UserGroupService.prototype.handleError = function (error) {
        // In a real world app, we might use a remote logging infrastructure
        //console.log(error);
        var errMsg;
        if (error instanceof __WEBPACK_IMPORTED_MODULE_1__angular_http__["f" /* Response */]) {
            var body = error.json() || '';
            var err = body.error || JSON.stringify(body);
            errMsg = error.status + " - " + (error.statusText || '') + " " + err;
        }
        else {
            errMsg = error.message ? error.message : error.toString();
        }
        //console.error(errMsg);
        return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].throw(errMsg);
    };
    return UserGroupService;
}());
UserGroupService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */]) === "function" && _a || Object])
], UserGroupService);

var _a;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/user-group.service.js.map

/***/ }),

/***/ "../../../../../src/app/security/user-role/index.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__user_role_component__ = __webpack_require__("../../../../../src/app/security/user-role/user-role.component.ts");
/* harmony namespace reexport (by used) */ __webpack_require__.d(__webpack_exports__, "a", function() { return __WEBPACK_IMPORTED_MODULE_0__user_role_component__["a"]; });

//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/index.js.map

/***/ }),

/***/ "../../../../../src/app/security/user-role/user-role.component.html":
/***/ (function(module, exports) {

module.exports = "<p-growl [value]=\"msgs\"></p-growl>\r\n<div class=\"main-panel\" style=\"min-height:110vh;\">\r\n<div *ngIf=\"!displayUserRoleDetails\">\r\n  <md-card>\r\n    <md-card-title color=\"primary\">Search User Roles</md-card-title>\r\n    <md-card-content>\r\n      <md-input placeholder=\"Name\" [(ngModel)]=\"userRoleSearchCriteria.roleName\" class=\"col-md-5\"></md-input>\r\n      <div class=\"col-md-2\"></div>\r\n      <md-input placeholder=\"Description\" [(ngModel)]=\"userRoleSearchCriteria.description\" class=\"col-md-5\"></md-input>\r\n    </md-card-content>\r\n    <md-card-actions class=\"center\">\r\n      <button md-raised-button color=\"primary\" (click)=\"searchUserRoles()\" *ngIf=\"authorizationService.isUserHasRole('UI_USER_ROLES_ACCESS')\">Search</button>\r\n      <button md-raised-button color=\"primary\" (click)=\"resetSearchCriteria()\">Reset</button>\r\n    </md-card-actions>\r\n  </md-card>\r\n  <br>\r\n  <br>\r\n  <p-dataTable [value]=\"userRoles\" selectionMode=\"single\" [responsive]=\"true\" (onRowSelect)=\"onRowSelect($event)\">\r\n    <header>User Roles</header>\r\n    <p-column field=\"roleName\" header=\"Name\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n    <p-column field=\"description\" header=\"Description\" [sortable]=\"true\" [filter]=\"true\"></p-column>\r\n    <p-footer>\r\n      <div class=\"row\">\r\n        <div class=\"col-md-12\">\r\n          <button md-raised-button color=\"primary\" (click)=\"createUserRole()\" *ngIf=\"authorizationService.isUserHasRole('UI_USER_ROLES_CREATE')\">Create</button>\r\n          <!--button md-raised-button color=\"primary\">Import</button-->\r\n        </div>\r\n      </div>\r\n    </p-footer>\r\n  </p-dataTable>\r\n</div>\r\n<div *ngIf=\"displayUserRoleDetails\">\r\n  <md-card>\r\n    <md-card-title color=\"primary\">Create User Role</md-card-title>\r\n    <md-card-content>\r\n      <div>\r\n        <md-input placeholder=\"Name\" [(ngModel)]=\"selectedUserRole.roleName\" class=\"col-md-5\"></md-input>\r\n        <div class=\"col-md-2\"></div>\r\n        <md-input placeholder=\"Description\" [(ngModel)]=\"selectedUserRole.description\" class=\"col-md-5\"></md-input>\r\n      </div>\r\n      <br>\r\n      <br>\r\n      <br>\r\n      <br>\r\n      <div>\r\n        <p-panel *ngFor=\"let authorityByModule of authoritiesByModule;\" [toggleable]=\"true\">\r\n          <header>\r\n            {{authorityByModule.moduleName}}\r\n          </header>\r\n          <table class=\"table table-bordered table-sm\">\r\n            <thead>\r\n              <tr>\r\n                <th class=\"text-center\">Grant</th>\r\n                <th class=\"text-center\">View</th>\r\n                <th class=\"text-center\">Create</th>\r\n                <th class=\"text-center\">Update</th>\r\n                <th class=\"text-center\">Delete</th>\r\n              </tr>\r\n            </thead>\r\n            <tbody>\r\n              <tr *ngFor=\"let authority of authorityByModule.authorityResources;\">\r\n                <td class=\"text-center\">{{authority.authorityConstant}}</td>\r\n                <td class=\"text-center\">\r\n                  <input type=\"checkbox\" *ngIf=\"authority.viewGrant\"  style=\"width: 20px; height: 20px; cursor: pointer;\" (click)=\"toggleUserRoleAuthority(authority.viewGrant)\" [checked]=\"existsUserRoleAuthority(authority.viewGrant)\">\r\n                </td>\r\n                <td class=\"text-center\">\r\n                  <input type=\"checkbox\" *ngIf=\"authority.createGrant\"  style=\"width: 20px; height: 20px; cursor: pointer;\" (click)=\"toggleUserRoleAuthority(authority.createGrant)\" [checked]=\"existsUserRoleAuthority(authority.createGrant)\">\r\n                </td>\r\n                <td class=\"text-center\">\r\n                  <input type=\"checkbox\" *ngIf=\"authority.updateGrant\"  style=\"width: 20px; height: 20px; cursor: pointer;\" (click)=\"toggleUserRoleAuthority(authority.updateGrant)\" [checked]=\"existsUserRoleAuthority(authority.updateGrant)\">\r\n                </td>\r\n                <td class=\"text-center\">\r\n                  <input type=\"checkbox\" *ngIf=\"authority.deleteGrant\"  style=\"width: 20px; height: 20px; cursor: pointer;\" (click)=\"toggleUserRoleAuthority(authority.deleteGrant)\" [checked]=\"existsUserRoleAuthority(authority.deleteGrant)\">\r\n                </td>\r\n              </tr>\r\n            </tbody>\r\n          </table>\r\n        </p-panel>\r\n      </div>\r\n    </md-card-content>\r\n    <md-card-actions class=\"center\">\r\n      <button md-raised-button color=\"primary\" (click)=\"submitNewUserRole()\" *ngIf=\"authorizationService.isUserHasRole('UI_USER_ROLES_UPDATE')\">Submit</button>\r\n      <button md-raised-button color=\"primary\" (click)=\"deleteNewUserRole()\" *ngIf=\"authorizationService.isUserHasRole('UI_USER_ROLES_DELETE')\">Delete</button>\r\n      <button md-raised-button color=\"primary\" (click)=\"backToUserRoles()\">Back</button>\r\n    </md-card-actions>\r\n  </md-card>\r\n</div>\r\n\r\n</div>"

/***/ }),

/***/ "../../../../../src/app/security/user-role/user-role.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__user_role_service__ = __webpack_require__("../../../../../src/app/security/user-role/user-role.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__model_user_role__ = __webpack_require__("../../../../../src/app/security/model/user.role.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__model_user_role_search_criteria__ = __webpack_require__("../../../../../src/app/security/model/user.role.search.criteria.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__core_authorization_service__ = __webpack_require__("../../../../../src/app/core/authorization.service.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserRoleComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var UserRoleComponent = (function () {
    function UserRoleComponent(userRoleService, authorizationService) {
        this.userRoleService = userRoleService;
        this.authorizationService = authorizationService;
        this.userRoleSearchCriteria = new __WEBPACK_IMPORTED_MODULE_3__model_user_role_search_criteria__["a" /* UserRoleSearchCriteria */]();
        this.authoritiesByModule = [];
        this.userRoleAuthorities = [];
        this.msgs = [];
    }
    UserRoleComponent.prototype.ngOnInit = function () {
        this.getAuthoritiesByModule();
    };
    UserRoleComponent.prototype.onRowSelect = function (event) {
        var _this = this;
        this.selectedUserRole = event.data;
        this.userRoleService.getUserRoleById(this.selectedUserRole.objectId)
            .subscribe(function (userRoleFromDB) {
            _this.selectedUserRole = userRoleFromDB;
            _this.userRoleAuthorities = _this.selectedUserRole.userRoleAuthorities;
            _this.displayUserRoleDetails = true;
            _this.updateUserRole = true;
        });
    };
    UserRoleComponent.prototype.searchUserRoles = function () {
        var _this = this;
        this.userRoleService.getUserRoles(this.userRoleSearchCriteria)
            .subscribe(function (userRoles) {
            _this.userRoles = userRoles;
        }, function (error) {
        });
    };
    ;
    UserRoleComponent.prototype.resetSearchCriteria = function () {
        this.userRoleSearchCriteria = new __WEBPACK_IMPORTED_MODULE_3__model_user_role_search_criteria__["a" /* UserRoleSearchCriteria */]();
    };
    ;
    UserRoleComponent.prototype.createUserRole = function () {
        this.selectedUserRole = new __WEBPACK_IMPORTED_MODULE_2__model_user_role__["a" /* UserRole */]();
        this.userRoleAuthorities = [];
        this.displayUserRoleDetails = true;
        this.updateUserRole = false;
    };
    ;
    UserRoleComponent.prototype.backToUserRoles = function () {
        this.selectedUserRole = new __WEBPACK_IMPORTED_MODULE_2__model_user_role__["a" /* UserRole */]();
        this.userRoleAuthorities = [];
        this.displayUserRoleDetails = false;
        this.updateUserRole = false;
    };
    ;
    UserRoleComponent.prototype.submitNewUserRole = function () {
        var _this = this;
        this.msgs = [];
        this.displayUserRoleDetails = false;
        this.selectedUserRole.userRoleAuthorities = this.userRoleAuthorities;
        if (this.updateUserRole) {
            this.userRoleService.updateUserRole(this.selectedUserRole)
                .subscribe(function () {
                _this.searchUserRoles();
                _this.msgs.push({ severity: "info", summary: "User role updated successfully.", detail: "" });
            }, function (error) {
                _this.msgs.push({ severity: "error", summary: "User role updation failed.", detail: error });
            });
        }
        else {
            this.userRoleService.createUserRole(this.selectedUserRole)
                .subscribe(function () {
                _this.searchUserRoles();
                _this.msgs.push({ severity: "info", summary: "User role created successfully.", detail: "" });
            }, function (error) {
                _this.msgs.push({ severity: "error", summary: "User role creation failed.", detail: error });
            });
        }
    };
    ;
    UserRoleComponent.prototype.deleteNewUserRole = function () {
        var _this = this;
        this.msgs = [];
        this.displayUserRoleDetails = false;
        this.userRoleService.deleteUserRoleById(this.selectedUserRole.objectId)
            .subscribe(function () {
            _this.searchUserRoles();
            _this.msgs.push({ severity: "info", summary: "User role deleted successfully.", detail: "" });
        }, function (error) {
            _this.msgs.push({ severity: "error", summary: "User role deletion failed.", detail: error });
        });
    };
    ;
    UserRoleComponent.prototype.getAuthoritiesByModule = function () {
        var _this = this;
        this.userRoleService.getAuthoritiesByModule()
            .subscribe(function (authorities) {
            _this.authoritiesByModule = authorities;
        }, function (error) {
        });
    };
    ;
    UserRoleComponent.prototype.toggleUserRoleAuthority = function (item) {
        var idx = this.userRoleAuthorities.indexOf(item);
        if (idx > -1) {
            this.userRoleAuthorities.splice(idx, 1);
        }
        else {
            this.userRoleAuthorities.push(item);
        }
    };
    ;
    UserRoleComponent.prototype.existsUserRoleAuthority = function (item) {
        return this.userRoleAuthorities.indexOf(item) > -1;
    };
    ;
    return UserRoleComponent;
}());
UserRoleComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'user-role',
        template: __webpack_require__("../../../../../src/app/security/user-role/user-role.component.html")
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__user_role_service__["a" /* UserRoleService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__user_role_service__["a" /* UserRoleService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_4__core_authorization_service__["a" /* AuthorizationService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__core_authorization_service__["a" /* AuthorizationService */]) === "function" && _b || Object])
], UserRoleComponent);

var _a, _b;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/user-role.component.js.map

/***/ }),

/***/ "../../../../../src/app/security/user-role/user-role.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__("../../../http/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__ = __webpack_require__("../../../../rxjs/Observable.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserRoleService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var UserRoleService = (function () {
    function UserRoleService(http) {
        this.http = http;
    }
    UserRoleService.prototype.getAuthoritiesByModule = function () {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        var options = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["d" /* RequestOptions */]({ headers: headers });
        return this.http.get('enumConstants/authorityConstants')
            .map(function (res) { return res.json(); }).catch(this.handleError);
        ;
    };
    UserRoleService.prototype.getUserRoles = function (userRoleSearchCriteria) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        headers.append('Access-Control-Allow-Origin', '*');
        return this.http.post('userRoles/search', JSON.stringify(userRoleSearchCriteria), { headers: headers })
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    UserRoleService.prototype.getUserRoleById = function (id) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.get('userRoles/' + id)
            .map(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    UserRoleService.prototype.createUserRole = function (userRole) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        headers.append('Access-Control-Allow-Origin', '*');
        return this.http.post('userRoles', JSON.stringify(userRole), { headers: headers })
            .map(function (res) { return; })
            .catch(this.handleError);
    };
    UserRoleService.prototype.deleteUserRoleById = function (id) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.delete('userRoles/' + id)
            .map(function (res) { return; })
            .catch(this.handleError);
    };
    UserRoleService.prototype.updateUserRole = function (userRole) {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_http__["c" /* Headers */]();
        headers.append('Content-Type', 'application/json');
        return this.http.put('userRoles' + "/" + userRole.objectId, JSON.stringify(userRole), { headers: headers })
            .map(function (res) { return; })
            .catch(this.handleError);
    };
    UserRoleService.prototype.extractData = function (res) {
        var body = res.json();
        console.log('body extractData ', res);
        return body.data || {};
    };
    UserRoleService.prototype.handleError = function (error) {
        // In a real world app, we might use a remote logging infrastructure
        //console.log(error);
        var errMsg;
        if (error instanceof __WEBPACK_IMPORTED_MODULE_1__angular_http__["f" /* Response */]) {
            var body = error.json() || '';
            var err = body.error || JSON.stringify(body);
            errMsg = error.status + " - " + (error.statusText || '') + " " + err;
        }
        else {
            errMsg = error.message ? error.message : error.toString();
        }
        //console.error(errMsg);
        return __WEBPACK_IMPORTED_MODULE_2_rxjs_Observable__["Observable"].throw(errMsg);
    };
    return UserRoleService;
}());
UserRoleService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["g" /* Http */]) === "function" && _a || Object])
], UserRoleService);

var _a;
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/user-role.service.js.map

/***/ }),

/***/ "../../../../../src/environments/environment.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return environment; });
// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `angular-cli.json`.
// The file contents for the current environment will overwrite these during build.
var environment = {
    production: false
};
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/environment.js.map

/***/ }),

/***/ "../../../../../src/main.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__polyfills_ts__ = __webpack_require__("../../../../../src/polyfills.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__ = __webpack_require__("../../../platform-browser-dynamic/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_core__ = __webpack_require__("../../../core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__environments_environment__ = __webpack_require__("../../../../../src/environments/environment.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__app___ = __webpack_require__("../../../../../src/app/index.ts");





if (__WEBPACK_IMPORTED_MODULE_3__environments_environment__["a" /* environment */].production) {
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_2__angular_core__["enableProdMode"])();
}
__webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_4__app___["a" /* AppModule */]);
//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/main.js.map

/***/ }),

/***/ "../../../../../src/polyfills.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_core_js_es6_symbol__ = __webpack_require__("../../../../core-js/es6/symbol.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_core_js_es6_symbol___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_core_js_es6_symbol__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_core_js_es6_object__ = __webpack_require__("../../../../core-js/es6/object.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_core_js_es6_object___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_core_js_es6_object__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_core_js_es6_function__ = __webpack_require__("../../../../core-js/es6/function.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_core_js_es6_function___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_core_js_es6_function__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_core_js_es6_parse_int__ = __webpack_require__("../../../../core-js/es6/parse-int.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_core_js_es6_parse_int___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_core_js_es6_parse_int__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_core_js_es6_parse_float__ = __webpack_require__("../../../../core-js/es6/parse-float.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_core_js_es6_parse_float___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_4_core_js_es6_parse_float__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_core_js_es6_number__ = __webpack_require__("../../../../core-js/es6/number.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_core_js_es6_number___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_5_core_js_es6_number__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_core_js_es6_math__ = __webpack_require__("../../../../core-js/es6/math.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_core_js_es6_math___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_6_core_js_es6_math__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7_core_js_es6_string__ = __webpack_require__("../../../../core-js/es6/string.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7_core_js_es6_string___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_7_core_js_es6_string__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8_core_js_es6_date__ = __webpack_require__("../../../../core-js/es6/date.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8_core_js_es6_date___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_8_core_js_es6_date__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9_core_js_es6_array__ = __webpack_require__("../../../../core-js/es6/array.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9_core_js_es6_array___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_9_core_js_es6_array__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10_core_js_es6_regexp__ = __webpack_require__("../../../../core-js/es6/regexp.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10_core_js_es6_regexp___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_10_core_js_es6_regexp__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11_core_js_es6_map__ = __webpack_require__("../../../../core-js/es6/map.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11_core_js_es6_map___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_11_core_js_es6_map__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12_core_js_es6_set__ = __webpack_require__("../../../../core-js/es6/set.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12_core_js_es6_set___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_12_core_js_es6_set__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13_core_js_es6_reflect__ = __webpack_require__("../../../../core-js/es6/reflect.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13_core_js_es6_reflect___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_13_core_js_es6_reflect__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14_core_js_es7_reflect__ = __webpack_require__("../../../../core-js/es7/reflect.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14_core_js_es7_reflect___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_14_core_js_es7_reflect__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15_zone_js_dist_zone__ = __webpack_require__("../../../../zone.js/dist/zone.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15_zone_js_dist_zone___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_15_zone_js_dist_zone__);
// This file includes polyfills needed by Angular 2 and is loaded before
// the app. You can add your own extra polyfills to this file.
















//# sourceMappingURL=D:/workspace-sts-3.7.0.RELEASE/emialapp-ui/src/polyfills.js.map

/***/ }),

/***/ 1:
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__("../../../../../src/main.ts");


/***/ })

},[1]);
//# sourceMappingURL=main.bundle.js.map