<template>
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-xl-3 col-md-6">
                    <stats-card>
                        <div slot="header" class="icon-warning">
                            <i class="nc-icon nc-chart text-warning"></i>
                        </div>
                        <div slot="content">
                            <p class="card-category">Messages</p>
                            <h4 class="card-title">{{messagesNumber}}</h4>
                        </div>
                        <div slot="footer">
                            <b-button name="refreshMessagesNumber" variant="bg-transparent" @click="refreshMessagesNumber"><i class="fa fa-refresh"></i>Update now</b-button>
                        </div>
                    </stats-card>
                </div>

                <div class="col-xl-3 col-md-6">
                    <stats-card>
                        <div slot="header" class="icon-success">
                            <i class="nc-icon nc-bulb-63 text-success"></i>
                        </div>
                        <div slot="content">
                            <p class="card-category">Top Currency Transaction</p>
                            <h4 class="card-title">{{topTotalCurrencyPairUsageDisplay}}</h4>
                        </div>
                        <div slot="footer">
                            <i class="fa fa-clock-o"></i>Total
                        </div>
                    </stats-card>
                </div>

                <div class="col-xl-3 col-md-6">
                    <stats-card>
                        <div slot="header" class="icon-danger">
                            <i class="nc-icon nc-send text-danger"></i>
                        </div>
                        <div slot="content">
                            <p class="card-category">Top Currency Transaction</p>
                            <h4 class="card-title">{{topMonthCurrencyPairUsageDisplay}}</h4>
                        </div>
                        <div slot="footer">
                            <i class="fa fa-clock-o"></i>This Month
                        </div>
                    </stats-card>
                </div>

                <div class="col-xl-3 col-md-6">
                    <stats-card>
                        <div slot="header" class="icon-info">
                            <i class="nc-icon nc-vector text-primary"></i>
                        </div>
                        <div slot="content">
                            <p class="card-category">Top Currency Transaction</p>
                            <h4 class="card-title">{{topWeekCurrencyPairUsageDisplay}}</h4>
                        </div>
                        <div slot="footer">
                            <i class="fa fa-clock-o"></i>This Week
                        </div>
                    </stats-card>
                </div>

            </div>
            <div class="row">
                <div class="col-md-8">
                    <chart-card ref="graph"
                            :chart-data="barChart.data"
                            :chart-options="barChart.options"
                            :chart-responsive-options="barChart.responsiveOptions"
                            chart-type="Bar">
                        <template slot="header">
                            <h4 class="card-title">Trends</h4>
                            <p class="card-category">Top Currency Transactions</p>
                        </template>
                        <template slot="footer">
                            <hr>
                            <div class="stats">
                                <i class="fa fa-check"></i> Data information certified
                            </div>
                        </template>
                    </chart-card>
                </div>

            </div>

        </div>
    </div>
</template>

<script src="./OverviewVM.js"></script>

<style>

</style>
