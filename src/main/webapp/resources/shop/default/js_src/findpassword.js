var findPassword = function(){
		init = function(){
			return this.commit();
		};
		commit = function(){
			alert(123);
		};
		
		return init;
};