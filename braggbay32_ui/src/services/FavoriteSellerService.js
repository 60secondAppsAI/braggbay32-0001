import http from "../http-common"; 

class FavoriteSellerService {
  getAllFavoriteSellers(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/favoriteSeller/favoriteSellers`, searchDTO);
  }

  get(favoriteSellerId) {
    return this.getRequest(`/favoriteSeller/${favoriteSellerId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/favoriteSeller?field=${matchData}`, null);
  }

  addFavoriteSeller(data) {
    return http.post("/favoriteSeller/addFavoriteSeller", data);
  }

  update(data) {
  	return http.post("/favoriteSeller/updateFavoriteSeller", data);
  }
  
  uploadImage(data,favoriteSellerId) {
  	return http.postForm("/favoriteSeller/uploadImage/"+favoriteSellerId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new FavoriteSellerService();
