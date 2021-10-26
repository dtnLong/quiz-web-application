import API from "./API";

const data = {
    code: "quiz04", 
	questions:[
		{
			questionNumber: 1,
			questionText: "The performance measurement approach that would be most useful in measuring and describing supply chain processes would be:",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "Strategic Profit Model"
				},
				{
					option: "B",
					correct: false,
					choiceText: "Balanced scorecard"
				},
                {
                    option: "C",
                    correct: true,
                    choiceText: "SCOR model"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "All of these"
                }
				

			]
		},
        {
            questionNumber: 2,
			questionText: "VinFast claims that its producing processes conduct fewer greenhouse gases than those of its rivals. This suggests that VinFast has a focus on:",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "Risk Management"
				},
				{
					option: "B",
					correct: true,
					choiceText: "Sustainability"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "Flexibility"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "Profitability"
                }
				

			]
        },
        {
            questionNumber: 3,
			questionText: "A Chinese firm that has a diversity of supplier relationships which helps it provide many kinds of products and services to many types of consumers. The business is still going well until the internal conflict of supplier A and B occurs and they could not work together. This forces the Chinese firm to considerably and carefully choose who they will continue working with due to the exit of one of them will make the firm lose their production and consumer as well. Which measurement could help this firm to make the right decision?",
			choices: [
				{
					option: "A",
					correct: true,
					choiceText: "Cost to serve"
				},
				{
					option: "B",
					correct: false,
					choiceText: "Transaction cost"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "Prescriptive"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "SRM"
                }
				

			]
        },
        {
            questionNumber: 4,
			questionText: "Which of the below scenarios is an example of inventory being held as a way to balance supply and demand?",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "A manufacturer holds inventory of key components to maintain a level production schedule."
				},
				{
					option: "B",
					correct: true,
					choiceText: "A manufacturer of seasonal products builds finished-goods inventory before the peak selling period."
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "A distributor maintains a safety stock of slow-moving items at a central distribution center."
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "A retailer stocks a variety of sizes and colors of a fast-selling item to avoid losing sales."
                }
				

			]
        },
        {
            questionNumber: 5,
			questionText: "Under the Covid 19 impact, It quickly became clear that suppliers for many firms in Vietnam were struggling to cope with the shock. However, Vietnam Hyundai Thanh Cong is still able to cope with this external circumstance quickly as their supply chain has mul-ti sources from many suppliers and an ability to shorten the distances among their global networks and shorten Lead Time to finish products “Just in Case”. Which  of the following words best describe the company’s supply chain?",
			choices: [
				{
					option: "A",
					correct: true,
					choiceText: "Resilient Strategy"
				},
				{
					option: "B",
					correct: false,
					choiceText: "Responsive strategy"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "Flexible strategy"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "Reliable strategy"
                }
			]
        },
        {
            questionNumber: 6,
			questionText: "A company has identified groups of consumers based on particular characteristics the consumers desire from products and services the company can supply. Characteristics include delivery time, pricing , and service levels. The company is engaging in which of the following practices?",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "Customer differentiation"
				},
				{
					option: "B",
					correct: false,
					choiceText: "Order winner segregation"
				},
                {
                    option: "C",
                    correct: true,
                    choiceText: "Market segmentation"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "Channel definition"
                }
				

			]
        },
        {
            questionNumber: 7,
			questionText: "A factory applies standard costing, and a potential supplier applies activity-based costing. This difference most likely will have implications for which of the following types of future decisions?",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "Price concessions"
				},
				{
					option: "B",
					correct: true,
					choiceText: "Make-or-buy"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "Distribution warehouse locations"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "Freight terms"
                }
				

			]
        },
        {
            questionNumber: 8,
			questionText: "The possibility  of customer dissatisfaction in the highest level occurs when?",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "Customer expectations are clearly defined."
				},
				{
					option: "B",
					correct: false,
					choiceText: "Supplier execution is too low."
				},
                {
                    option: "C",
                    correct: true,
                    choiceText: "There is a gap between expected performance and perceived performance."
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "There is a gap between what was indicated and what was accomplished"
                }
				

			]
        },
        {
            questionNumber: 9,
			questionText: "A producer of steel components that are sold either directly or through distributors wants to identify the demand of the end customers for each market segment. Which of the following practices would be most correct?",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "Analyzing the buying history for each of the market segments"
				},
				{
					option: "B",
					correct: false,
					choiceText: "Asking the manufacturer's direct customers"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "Sending samples of potential future products to the final customers"
                },
                {
                    option: "D",
                    correct: true,
                    choiceText: "Conducting a market research project"
                }
				

			]
        },
        {
            questionNumber: 10,
			questionText: "A manufacturer has special products for a group of consumers. When demand for these products falls, the manufacturer can alter the production line to a commodity-type product which can be sold on the open market at declined terms to earn cash. Which strategy that the factory is implementing:",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "customer focus and alignment"
				},
				{
					option: "B",
					correct: false,
					choiceText: "forecast accuracy"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "multiple downstream channels"
                },
                {
                    option: "D",
                    correct: true,
                    choiceText: "multiple upstream supply chains"
                }
				

			]
        },
        {
            questionNumber: 11,
			questionText: "You are the Transportation Manager for a manufacturing and distribution firm. Client demand for your product has recently increased, and your company is struggling to meet customer orders on time. Furthermore, your company's truck fleet is at capacity, necessitating the hiring of additional Logistics and Transport businesses to meet the increased shipping demands. You are in charge of scheduling material shipments between locations at the lowest possible cost while keeping an eye on material availability at each warehouse site. As a result, your task is to utilize the data given to input into Table 1 to determine the optimal feasible solution to decrease freight costs while meeting consumer demand and find out the optimal cost for the firm.\nHints: applying The Least Cost Method for the initial feasible solution and The Modified Distribution method for the optimal test.\nhttps://res.cloudinary.com/logisticomss6/image/upload/v1635219657/logis-test/quiz04-q11a_gddeq4.png\nTable 1: Worksheet used to calculate and find the best solution.\nThe following table is data presented for you to input into Table 1 and calculate possible solutions to the problem\nhttps://res.cloudinary.com/logisticomss6/image/upload/v1635219852/logis-test/quiz04-q11b_hxek0m.png\nThe optimal cost is:",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "$890"
				},
				{
					option: "B",
					correct: false,
					choiceText: "$990"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "$620"
                },
                {
                    option: "D",
                    correct: true,
                    choiceText: "$630"
                }
				

			]
        },
        {
            questionNumber: 12,
			questionText: "Apple provides extensive supply chain visibility to the customer across its brick-and-mortar and online retail formats to enable the customer to pick the desired model and product variation, Instead of just being able to go to a traditional retail store, a customer can go to an Apple-owned store, the Apple website, or the Apple mobile app; configure a product with a host of optional attributes; know precisely where and when it is available at any of Apple’s stores nationwide or online; and then select in-store pickup, express shipment, or regular parcel post. Which of the following characteris that Apple’s supply chain has provide its customers:",
			choices: [
				{
					option: "A",
					correct: true,
					choiceText: "Multiple-channels Fulfillment"
				},
				{
					option: "B",
					correct: false,
					choiceText: "Retail channel"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "Distribution channel"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "Omni channel"
                }
				

			]
        },
        {
            questionNumber: 13,
			questionText: "A company provides a single line of products to consumers using retail stores and on-line sales, distributors, and wholesalers. Currently the firm has common pricing and response times for sales in each sales channel. Which of the following strategy is most appropriate to apply to enhances",
			choices: [
				{
					option: "A",
					correct: true,
					choiceText: "Customer segmentation"
				},
				{
					option: "B",
					correct: false,
					choiceText: "Customer-facing ordering systems"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "Customer relationship management (CRM)"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "Supply Chain Operations Reference (SCOR)"
                }
				

			]
        },
        {
            questionNumber: 14,
			questionText: "Forecasting technique is similar to the use of a seasonal to  measure the ratio of the:",
			choices: [
				{
					option: "A",
					correct: true,
					choiceText: "average seasonal demand to the average demand for all periods"
				},
				{
					option: "B",
					correct: false,
					choiceText: "average demand for all periods to the average seasonal demand"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "average seasonal demand to the standard deviation of the demand for all periods"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "standard deviation of the seasonal demand to the standard deviation of demand for all periods"
                }
				

			]
        },
        {
            questionNumber: 15,
			questionText: "X is a fashion retailer. X wants to know the reorder point of its popular sweaters. Last month (30 days), the number of sweaters sold was 95. The maximum sweaters X sold a day was 10.\nX orders their sweaters from a local supplier. It takes one day for the order to be approved, one week from the order date for it to arrive at X’s warehouse, and one day to receive, process, and shelve the new order upon delivery. (supply Delay :9)\nUnfortunately, X’s supplier only accepts orders on Wednesdays.\nDue to unexpected seasonal demand, X’s supplier was also running low on product, adding an additional 2 days to get stock in from their manufacturer. Plus, the trucks making the delivery were slowed down by bad weather, causing an additional day of delay. (reorder delay 6 days)",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "91"
				},
				{
					option: "B",
					correct: true,
					choiceText: "180"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "90"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "59"
                }
				

			]
        },
        {
            questionNumber: 16,
			questionText: "Company X would like to develop an aggregate plan via the transportation method. Data that relate to production, demand, capacity, and cost at its main plant are shown in the following table. Your task is to solve the aggregate planning problem by minimizing the costs of matching production in various periods to future demands and find out the total cost of your aggregate plan.\nhttps://res.cloudinary.com/logisticomss6/image/upload/v1635220393/logis-test/quiz04-q16a_trb9dy.png\n\nhttps://res.cloudinary.com/logisticomss6/image/upload/v1635220512/logis-test/quiz04-16b_xaryt6.png\n\nNotes: Back ordering is not viable",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "$124,990"
				},
				{
					option: "B",
					correct: true,
					choiceText: "$153,990"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "$646,990"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "$675,250"
                }
				

			]
        },
        {
            questionNumber: 17,
			questionText: "An American firm named Evans Cycle has been selling racing cycles at Road street for many years. But, it becomes more competitive when there is a new firm selling more types of cycles that exist nearby and attract more consumers. Therefore, the manager decided to conduct product development projections for a new product to entice customers. Which strategy is the Evans Cycle applying ?",
			choices: [
				{
					option: "A",
					correct: true,
					choiceText: "Demand shaping"
				},
				{
					option: "B",
					correct: false,
					choiceText: "Demand Forecasting"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "Demand Planning"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "Demand Sensing"
                }
				

			]
        },
        {
            questionNumber: 18,
			questionText: "A Motorcycle company is implementing a focus on just-in-time production system to reduce the overall amount of time to finish a product. Also they expand this strategy to change their Outbound transportation. Instead of hold-shipments for cost-saving opportunities it is turned into small shipments direct to customers in urban areas as soon as finished products are available. Which of the following strategies is the company driving in?",
			choices: [
				{
					option: "A",
					correct: true,
					choiceText: "Responsive Supply Chain"
				},
				{
					option: "B",
					correct: false,
					choiceText: "Flexible Supply Chain"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "Reliable Supply Chain"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "Resilient Supply Chain"
                }
				

			]
        },
        {
            questionNumber: 19,
			questionText: "A logistics and supply chain manager would like to know the total cost of a chase strategy that matches the forecast below using a steady regular production rate of 400 units a month, a maximum of 40 units per month of overtime, and subcontracting as needed to make up any shortages. The unit costs are:\nRegular production = $45\nOvertime = $85\nSubcontracting = $95\nhttps://res.cloudinary.com/logisticomss6/image/upload/v1635220839/logis-test/quiz04-q19_fnpjy4.png\nWhat is the total cost of a chase strategy?",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "$155,950"
				},
				{
					option: "B",
					correct: false,
					choiceText: "$156,600"
				},
                {
                    option: "C",
                    correct: true,
                    choiceText: "$131,650"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "$130,950"
                }
				

			]
        },
        {
            questionNumber: 20,
			questionText: "COVID-19 has seen Vietnam start to concern about the safety and efficiency of the supply of essential products such as healthcare and pharmaceuticals. Therefore, many firms are now developing the ability to reduce geographic dependence in their global networks and shorten cycle times for finished products by bringing production back to domestic markets to be adaptable with the dramatical change of demand. Which of the following resilient strategy the company follow:",
			choices: [
				{
					option: "A",
					correct: true,
					choiceText: "Nearshoring"
				},
				{
					option: "B",
					correct: false,
					choiceText: "Offshoring"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "Onshoring"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "Outsourcing"
                }
				

			]
        },
        {
            questionNumber: 21,
			questionText: "A paper supply chain compromise that would support delivering the materials on time for the manufacturer. However, the supply could not be delivered on time due to the severe weather. It’s customers have been waiting lately for more than 2 weeks compared to the previous contracted time. Since the supply arrives, the manufacturer implements product checking and it turns out that half of the supply is all wet. Which of the following competitive priorities the company is false at?",
			choices: [
				{
					option: "A",
					correct: true,
					choiceText: "Reliability"
				},
				{
					option: "B",
					correct: false,
					choiceText: "Responsiveness"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "Realignment"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "Resiliency"
                }
				

			]
        },
        {
            questionNumber: 22,
			questionText: "Company B is choosing between 2 alternative Order Plans for its inventory of a certain product. The annual demand for the product is expected to be 1000 units.\n\"Plan 1\":\n\t+ order cost would be $60 per order\n\t+ inventory holding cost would be $20 per unit\n\"Plan 2\":\n\t+ order cost would be $40 per order\n\t+ inventory holding cost would be 20% and unit cost is $30\nFind out \"EOQ and total inventory cost\" and decide \"which plan\" would lead to the lower total inventory cost?",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "EOQ1 = 6000; EOQ2 = 13; Plan 1"
				},
				{
					option: "B",
					correct: true,
					choiceText: "EOQ1 =  77; EOQ2 = 115; Plan 2"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "EOQ1 = 6000; EOQ2 = 13; Plan 2"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "EOQ1 = 77; EOQ2 = 115; Plan 1"
                }
				

			]
        },
        {
            questionNumber: 23,
			questionText: "After recovering from the first attack of COVID 19 pandemic, a manufacturer and wholesaler of jams and jellies. They invest a large amount of money to update their warehouse to be larger and more automotive. When the updated warehouse is completed they buy and keep extra levels of raw materials like fruit and sugar on hand to account for disruptions from their supplier in case the COVID 19 is back. This ensures their production is still able to run in the unexpected event which causes the shortage of raw materials. Which strategy did the firm apply?",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "Square inventory"
				},
				{
					option: "B",
					correct: false,
					choiceText: "Anticipation Inventory"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "inventory control"
                },
                {
                    option: "D",
                    correct: true,
                    choiceText: "Buffer Inventory"
                }
				

			]
        },
        {
            questionNumber: 24,
			questionText: "Company Logisticom compiles all the possible changing market conditions to develop new appropriate products.Then the company assigns probabilities to each possible outcome that has more than a 15% likelihood of occurring. The company is planning effectively to cover nearly 80% of the possible outcomes which could adapt quickly with the changing demand, thereby increasing its flexibility and resilience.",
			choices: [
				{
					option: "A",
					correct: true,
					choiceText: "Range Forecasting"
				},
				{
					option: "B",
					correct: false,
					choiceText: "Seasonality Forecast"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "Straight-line forecasting"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "Linear regression forecast"
                }
				

			]
        },
        {
            questionNumber: 25,
			questionText: "A new firm entering a new market imported a number of inventory to stock. They used traditional forecasting models based on time series techniques that create a forecast based on prior sales history to predict mid-long term future. However, The new market frequently has a constant change of demand in days.Therefore, the traditional methods have shown to be less accurate, making the firm struggle in optimizing available inventory and balancing its network in regional warehouses. The firm’s manager is planning to invest to enhance their forecasting methods. Which of the following is the most appropriate one?",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "Demand shaping"
				},
				{
					option: "B",
					correct: false,
					choiceText: "Demand Forecasting"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "Demand Planning"
                },
                {
                    option: "D",
                    correct: true,
                    choiceText: "Demand Sensing"
                }
				
			]
        }
    ]

}






export default{
	addQuiz(){
		return API().post('/api/temp/quiz', data);
   
	}
}
export {data};
