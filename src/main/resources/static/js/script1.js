console.log("this is script");
const toggleSidebar = () =>{
	if($(".sidebar").is(":visible")){
		
		//true 
		//band karna hai
		
		$(".sidebar").css("display","none");
		$(".content").css("margin-left","0%");
	}else{
		//false
		//show krna hai
		$(".sidebar").css("display","block");
		$(".content").css("margin-left","20%");
	}
};
//first request-to server to create order

const paymentStart=()=>{
	console.log("payment started....");
	let amount=$("#payment_field").val();
	console.log(amount);
	if(amount==""||amount==null)
	{
		//alert("amount is required!!!");
		swal("Failed!", "amount is required!!!", "error");
		return;
	}
	
	//code...
	//we will use ajax to send request to server to create order
	$.ajax(
		{
			
			url:"/user/create_order",
			data:JSON.stringify({amount:amount,info:"order_request"}),
			contentType:"application/json",
			
			type:"POST",
			dataType:"json",
			success:function(response){
				//invoked when success
				if(response.status=="created"){
					//open payment form
					let options={
						key:"rzp_test_PHr2cHwZcE2llZ",
						amount:response.amount,
						currency:"INR",
						name:"EWeasy",
						description:"Payment",
						order_id:response.id,
						handler:function(response){
							console.log(response.razorpay_payment_id)
							console.log(response.razorpay_order_id)
							console.log(response.razorpay_signature)
							console.log('payment successful!!!')
							//alert("congrats!!!Payment successful!!!")
							updatePaymentOnServer(
								response.razorpay_payment_id,
								response.razorpay_order_id,
								"paid");
						
						},
						"prefill": {
"name": "",
"email": "",
"contact": "" 
},
notes:{
	address:"LearnCodeWith Durgesh"
},"theme": {
"color": "#3399cc"
},
					};
					let rzp=new Razorpay(options);
					rzp.on("payment.failed", function (response){
console.log(response.error.code);
console.log(response.error.description);
console.log(response.error.source);
console.log(response.error.step);
console.log(response.error.reason);
console.log(response.error.metadata.order_id);
console.log(response.error.metadata.payment_id);
//alert("Oops payment failed!!!");
swal("Failed!", "Oops payment failed!!!", "error");	
});
rzp.open();
				}
				//console.log(response)
			},
			error:function(error){
				//invoked when error
				console.log(error)
				alert("Something went wrong!!!")
				
			},
		});
		 $('#form').submit(function (e) {
      e.preventDefault();
  });
  
};


//
function updatePaymentOnServer(payment_id,order_id,status)
{
	$.ajax(
		{
			
			url:"/user/update_order",
			data:JSON.stringify({
				payment_id:payment_id,
				order_id:order_id,
				status:status,
				}),
			contentType:"application/json",
			
			type:"POST",
			dataType:"json",
			success:function(response){
				swal("Good job!", "Congrats!!!Payment successful!!!", "success");
			},
			error:function(error){
			swal("Failed!", "Your payment is successful,but we didnot get on server,we will contact you as soon as possible", "error");	
			},
			});
}