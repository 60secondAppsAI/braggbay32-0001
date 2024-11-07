<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <favoriteSeller-table
            v-if="favoriteSellers && favoriteSellers.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:favoriteSellers="favoriteSellers"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-favorite-sellers="getAllFavoriteSellers"
             >

            </favoriteSeller-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import FavoriteSellerTable from "@/components/FavoriteSellerTable";
import FavoriteSellerService from "../services/FavoriteSellerService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    FavoriteSellerTable,
  },
  data() {
    return {
      favoriteSellers: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllFavoriteSellers(sortBy='favoriteSellerId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await FavoriteSellerService.getAllFavoriteSellers(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.favoriteSellers.length) {
					this.favoriteSellers = response.data.favoriteSellers;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching favoriteSellers:", error);
        }
        
      } catch (error) {
        console.error("Error fetching favoriteSeller details:", error);
      }
    },
  },
  mounted() {
    this.getAllFavoriteSellers();
  },
  created() {
    this.$root.$on('searchQueryForFavoriteSellersChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllFavoriteSellers();
    })
  }
};
</script>
<style></style>
