import ChartCard from 'src/components/UIComponents/Cards/ChartCard.vue'
import StatsCard from 'src/components/UIComponents/Cards/StatsCard.vue'
import Card from 'src/components/UIComponents/Cards/Card.vue'
import LTable from 'src/components/UIComponents/Table.vue'
import Checkbox from 'src/components/UIComponents/Inputs/Checkbox.vue'

export default {
  components: {
    Checkbox,
    Card,
    LTable,
    ChartCard,
    StatsCard
  },
  data: function () {
    return {
      currencyPairTotalUsages: [],
      currencyPairMonthUsages: [],
      currencyPairWeekUsages: [],
      messagesNumber: 0,
      topTotalCurrencyPairUsage: null,
      topMonthCurrencyPairUsage: null,
      topWeekCurrencyPairUsage: null,
      editTooltip: 'Edit Task',
      deleteTooltip: 'Remove',
      barChart: {
        data: {
          labels: [],
          series: []
        },
        options: {
          seriesBarDistance: 10,
          axisX: {
            showGrid: false
          },
          height: '245px'
        }
      }
    }
  },
  created () {
    Promise.all([this.getTopTenTotalCurrencyPairUsages(), this.getTopTenMonthCurrencyPairUsages(), this.getTopTenWeekCurrencyPairUsages(), this.getMessagesNumber()])
            .then(([currencyPairTotalUsages, currencyPairMonthUsages, currencyPairWeekUsages, messagesNumber]) => {
              this.currencyPairTotalUsages = currencyPairTotalUsages.data._embedded.currencyPairUsages
              this.currencyPairMonthUsages = currencyPairMonthUsages.data._embedded != null ? currencyPairMonthUsages.data._embedded.currencyPairUsages : []
              this.currencyPairWeekUsages = currencyPairWeekUsages.data._embedded != null ? currencyPairWeekUsages.data._embedded.currencyPairUsages : []
              this.messagesNumber = messagesNumber.data
              this.topTotalCurrencyPairUsage = this.getTopCurrencyPairUsage(this.currencyPairTotalUsages)
              this.topMonthCurrencyPairUsage = this.getTopCurrencyPairUsage(this.currencyPairMonthUsages)
              this.topWeekCurrencyPairUsage = this.getTopCurrencyPairUsage(this.currencyPairWeekUsages)
              this.initializeGraph()
            })
  },
  computed: {
    topTotalCurrencyPairUsageDisplay () {
      return this.topTotalCurrencyPairUsage != null ? this.topTotalCurrencyPairUsage.displayName : ''
    },
    topMonthCurrencyPairUsageDisplay () {
      return this.topMonthCurrencyPairUsage != null ? this.topMonthCurrencyPairUsage.displayName : ''
    },
    topWeekCurrencyPairUsageDisplay () {
      return this.topWeekCurrencyPairUsage != null ? this.topWeekCurrencyPairUsage.displayName : ''
    }
  },
  methods: {
    getTopTenTotalCurrencyPairUsages () {
      return this.$http.get('currencyPairUsages/search/findTopTenTotal?projection=inlinedCurrencyPairUsage')
    },
    getTopTenMonthCurrencyPairUsages () {
      return this.$http.get('currencyPairUsages/search/findTopTenMonth?projection=inlinedCurrencyPairUsage')
    },
    getTopTenWeekCurrencyPairUsages () {
      return this.$http.get('currencyPairUsages/search/findTopTenWeek?projection=inlinedCurrencyPairUsage')
    },
    getMessagesNumber () {
      return this.$http.get('messages/search/findTotal')
    },
    refreshMessagesNumber () {
      this.getMessagesNumber().then(response => {
        this.messagesNumber = response.data
      }).catch(e => this.messageNumber = 0)
    },
    getTopCurrencyPairUsage (currencyPairUsages) {
      return currencyPairUsages[0]
    },
    initializeGraph () {
      let _self = this
      this.barChart.data.labels = this.currencyPairTotalUsages.map(currencyPairUsage => currencyPairUsage.displayName)
      let appearances = []
      let appearancesShown = []
      this.barChart.data.labels.forEach(label => appearances.push(
        _self.currencyPairTotalUsages
          .filter(currencyPairTotalUsage => currencyPairTotalUsage.displayName === label)
          .map(currencyPairTotalUsage => currencyPairTotalUsage.appearances)
      ))
      appearances.forEach(appearance => appearancesShown.push(appearance[0]))
      this.barChart.data.series.push(appearancesShown)
      this.$refs.graph.initChart()
    }
  }
}
