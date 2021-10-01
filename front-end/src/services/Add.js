import API from "./API";

const data = {
    code: "quiz01", 
	questions:[
		{
			questionNumber: 1,
			questionText: "The reason for holding safety stock is?",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "To improve stock holding levels."
				},
				{
					option: "B",
					correct: false,
					choiceText: "To improve stock accuracy levels."
				},
                {
                    option: "C",
                    correct: true,
                    choiceText: "To cater for demand forecast errors."
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "To cater for a large storage capacity."
                }
				

			]
		},
        {
            questionNumber: 2,
			questionText: "Which of the following statements is true?",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "A 3PL company is a professional logistics service provider meeting the logistics requirements of an organization and can also integrate its resources and capability in order to provide comprehensive solutions to its customers."
				},
				{
					option: "B",
					correct: false,
					choiceText: "A 4PL company is a professional LSP meeting the logistics requirements of an organization and can also integrate its resources and technology in order to provide comprehensive solutions to its customers."
				},
                {
                    option: "C",
                    correct: true,
                    choiceText: "A 3PL is a logistics service provider meeting only the logistics requirements of an organization while 4PL is a logistics service provider meeting the logistics requirements of an organization and can also integrate its resources, capability and technology to provide comprehensive solutions to its customers."
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "All of the above."
                }
				

			]
        },
        {
            questionNumber: 3,
			questionText: "When a retailer aims to achieve leadership in cost, what is likely to happen?",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "Customers will like to have convenience and the retailers will also set up a number of stores at different locations in order to provide convenience to the customers."
				},
				{
					option: "B",
					correct: false,
					choiceText: "Customers will prefer a large variety of products and the retailer will also keep a large variety in the store in order to satisfy customer needs."
				},
                {
                    option: "C",
                    correct: true,
                    choiceText: "Customers will prefer a large variety of products but the retailer will not keep a large variety in the store in order to achieve economies of scale."
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "Customers will prefer a short lead time for their products and the retailer will keep a higher amount of inventory in order to satisfy customer needs in time."
                }
				

			]
        },
        {
            questionNumber: 4,
			questionText: "Cycle counting:",
			choices: [
				{
					option: "A",
					correct: true,
					choiceText: "Can reduce lost production"
				},
				{
					option: "B",
					correct: false,
					choiceText: "Can be done by all personnel, including clerical staff"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "Is more concerned with correcting errors in inventory counts"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "Is slow to detect problems"
                }
				

			]
        },
        {
            questionNumber: 5,
			questionText: "Company B is choosing between 2 alternative Order Plans for its inventory of a certain product. The annual demand for the product is expected  to be 15,000 units.\nPlan 1:\nOrder cost would be $0.55 per order\nInventory holding cost would be $1,37 per unit\nPlan 2:\nOrder cost would be $0.41 per order\nInventory holding cost would be 20% and unit cost is $6,57\nFind out EOQ and total inventory cost and decide which plan would lead to the lower total inventory cost?",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "EOQ1 = 110; EOQ2 = 82; Plan 1"
				},
				{
					option: "B",
					correct: true,
					choiceText: "EOQ1 = 110; EOQ2 = 82; Plan 2"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "EOQ1 = 82; EOQ2 = 110; Plan 1"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "EOQ1 = 82; EOQ2 = 110; Plan 2"
                }
			]
        },
        {
            questionNumber: 6,
			questionText: "Company A wants to calculate CLV (Customer Lifetime Value) with following information:\n-Total sales revenue(annual): $1,000,000\n-Total number of orders (annual): 40,000\n-Total number of unique(annual):15,000\n-Cost of goods sold: $470,000 (Beginning inventory: $180,000 ; Ending inventory: $160,000 ; Additional Purchases: $450,000)\n-Customer churn rate: 60%\nWhat is the \"CLV\" of Company A? \nGiven: CLV= AOV x F x GM x (1/R)",
			choices: [
				{
					option: "A",
					correct: true,
					choiceText: "$45.7"
				},
				{
					option: "B",
					correct: false,
					choiceText: "$61,3"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "$16,42"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "$73,57"
                }
				

			]
        },
        {
            questionNumber: 7,
			questionText: "Your company has a very slow reaction to changes in customer demand. In addition, they have excessive levels of inventory, a high customer complaint rate and poor delivery performance. Executive Management has been advised to move from a Push to a Pull environment. What characteristics of a Pull environment should improve the situation?",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "Supply chain is aligned to supplier product availability."
				},
				{
					option: "B",
					correct: true,
					choiceText: "Factory production is tightly synchronized to customer orders."
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "Demand management and supply chain management are managed independently."
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "Replenishment is determined by safety stock, safety lead time, lot sizing queues and setups."
                }
				

			]
        },
        {
            questionNumber: 8,
			questionText: "Building a customer-centric supply chain strategy is ____",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "Developing a supply chain “from the inside out”, focusing on the supply chain process to deliver the best service to customers."
				},
				{
					option: "B",
					correct: true,
					choiceText: "Developing a supply chain “from the outside in”, starting from the end-customer perspective."
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "Developing a supply chain with a great customer-service focus to satisfy customer’s expectation."
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "Developing an effective marketing supply chain to understand customer’s insights."
                }
				

			]
        },
        {
            questionNumber: 9,
			questionText: "The performance measurement approach that would be most useful in measuring and describing supply chain processes would be:",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "Strategic profit model."
				},
				{
					option: "B",
					correct: false,
					choiceText: "Balanced scorecard."
				},
                {
                    option: "C",
                    correct: true,
                    choiceText: "SCOR model."
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "All of these."
                }
				

			]
        },
        {
            questionNumber: 10,
			questionText: "Which of the following strategies is used primarily for demand planning?",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "Build-to-order scheduling."
				},
				{
					option: "B",
					correct: false,
					choiceText: "Push-pull replenishment."
				},
                {
                    option: "C",
                    correct: true,
                    choiceText: "Collaborative planning, forecasting, and replenishment."
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "Vendor-managed inventory."
                }
				

			]
        },
        {
            questionNumber: 11,
			questionText: "A firm wants to lose customers that don't value the unique products and services the firm offers and to attract and retain customers that want what the firm offers. Engaging in this activity should allow the firm to:",
			choices: [
				{
					option: "A",
					correct: true,
					choiceText: "Create a more loyal customer base."
				},
				{
					option: "B",
					correct: false,
					choiceText: "Improve customer satisfaction rates."
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "Increase market share."
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "Target higher-profit customers."
                }
				

			]
        },
        {
            questionNumber: 12,
			questionText: "Company A is the e-commerce firm B2B that provides a platform for SMEs to list, sell, and distribute their products. Company A's clients can now follow transactions from listing through delivery thanks to a new agreement with UPS. It generates______. for its consumers by enabling them to choose how, when and where their order is to be received.",
			choices: [
				{
					option: "A",
					correct: true,
					choiceText: "A tailored experience."
				},
				{
					option: "B",
					correct: false,
					choiceText: "A good customer service."
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "A customer-centric service."
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "An agile supply chain."
                }
				

			]
        },
        {
            questionNumber: 13,
			questionText: "You are an upper-level manager in a firm. You believe that corporate objectives are not effectively disseminated throughout the organization and that line-level managers do not take them into account in their decision making. Which of the following would best help you to try to correct this problem?",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "Strategic profit model."
				},
				{
					option: "B",
					correct: true,
					choiceText: "Balanced scorecard model."
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "Maslow's needs model."
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "SCOR model."
                }
				

			]
        },
        {
            questionNumber: 14,
			questionText: "Xanadu Inc. claims that its manufacturing processes result in fewer greenhouse gases than those of its primary competitors. This suggests that Xanadu has a focus on:",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "Risk management."
				},
				{
					option: "B",
					correct: true,
					choiceText: "Sustainability."
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "Flexibility."
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "Profitability."
                }
				

			]
        },
        {
            questionNumber: 15,
			questionText: "Which of the following is considered a \"process-related\" competitive priority?",
			choices: [
				{
					option: "A",
					correct: true,
					choiceText: "Flexibility"
				},
				{
					option: "B",
					correct: false,
					choiceText: "Quality"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "Cost"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "All of these"
                }
				

			]
        },
        {
            questionNumber: 16,
			questionText: "A local distributor expects to sell approximately 8900 tables of a certain size. Annual carrying cost is $12 per unit, and ordering cost is $85 per order. The distributor operates 300 days a year. What is the length of an order cycle if the EOQ is ordered?",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "9 working days"
				},
				{
					option: "B",
					correct: false,
					choiceText: "7 working days"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "11 working days"
                },
                {
                    option: "D",
                    correct: true,
                    choiceText: "12 working days"
                }
				

			]
        },
        {
            questionNumber: 17,
			questionText: "X is a fashion retailer. X wants to know the reorder point of its popular sweaters. Last month (30 days), the number of sweaters sold was 85. The maximum sweaters X sold a day was 5. X orders their sweaters from a local supplier. It takes one day for the order to be approved, one week from the order date for it to arrive at X’s warehouse, and one day to receive, process, and shelve the new order upon delivery.\nUnfortunately, X’s supplier only accepts orders on Wednesdays.\nDue to unexpected seasonal demand, X’s supplier was also running low on product, adding an additional 2 days to get stock in from their manufacturer. Plus, the trucks making the delivery were slowed down by bad weather, causing an additional day of delay.",
			choices: [
				{
					option: "A",
					correct: true,
					choiceText: "91"
				},
				{
					option: "B",
					correct: false,
					choiceText: "61"
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
            questionNumber: 18,
			questionText: "Convenience stores in Korea are regarded as one-stop shops, and they frequently handle package delivery services, allowing consumers to pick up their goods with their shopping essentials. The firm has now teamed with company B, an American online meal ordering and delivery company, to deliver groceries and home items. This collaboration enables consumers to receive a single,________delivery rather than having to organize multiple separate ones.",
			choices: [
				{
					option: "A",
					correct: true,
					choiceText: "Tailored"
				},
				{
					option: "B",
					correct: false,
					choiceText: "Flexible"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "Fast"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "Timely"
                }
				

			]
        },
        {
            questionNumber: 19,
			questionText: "Which of the following statements is not true regarding the value proposition?",
			choices: [
				{
					option: "A",
					correct: true,
					choiceText: "It offers the highest product quality in each quality dimension."
				},
				{
					option: "B",
					correct: false,
					choiceText: "It differentiates the firm from its competition in a way that is difficult to imitate."
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "It satisfies the financial and strategic objectives of the firm."
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "It can be reliably delivered given the operational capabilities of the firm and its supporting supply chain."
                }
				

			]
        },
        {
            questionNumber: 20,
			questionText: " _____ is desirable so the company can react swiftly to changes in the volume and mix of their products.",
			choices: [
				{
					option: "A",
					correct: true,
					choiceText: "Process flexibility"
				},
				{
					option: "B",
					correct: false,
					choiceText: "Machine flexibility"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "Work cell flexibility"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "Total quality management"
                }
				

			]
        },
        {
            questionNumber: 21,
			questionText: "A firm supplies products and services to a wide variety of industries with varying requirements for responsiveness and reliability. Many customers across these industries are not satisfied with the firm's ability to meet the lead time and on time delivery requirements. Which of the following tools is most appropriate for the firm to use to improve customer service?",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "Customer service ratio metrics"
				},
				{
					option: "B",
					correct: true,
					choiceText: "Market segmentation"
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
            questionNumber: 22,
			questionText: "Which of the following consequences is a result of shipping directly from the point of manufacture to the customer rather than through a distribution network?",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "Delivery lead times are consistent."
				},
				{
					option: "B",
					correct: true,
					choiceText: "Risk pooling benefits are negated."
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "Distribution overhead is increased."
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "Order-fill rate is decreased."
                }
				

			]
        },
        {
            questionNumber: 23,
			questionText: "Products likely to have seasonal or periodic demand patterns include:",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "Toilet paper and paper towels"
				},
				{
					option: "B",
					correct: false,
					choiceText: "Toothpaste and deodorant"
				},
                {
                    option: "C",
                    correct: true,
                    choiceText: "Skis and lawnmowers"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "Milk and meat"
                }
				

			]
        },
        {
            questionNumber: 24,
			questionText: "In purchasing an item or service from a supplier, which factor is not included when specifications are being developed?",
			choices: [
				{
					option: "A",
					correct: true,
					choiceText: "Delivery requirement"
				},
				{
					option: "B",
					correct: false,
					choiceText: "Quantity requirements"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "Price requirements"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "Functional requirement"
                }
				

			]
        },
        {
            questionNumber: 25,
			questionText: "The company A wants to calculate quarterly churn rate to measure how many of its customers leave within a specific period of time:\nMonth 1: Company A has 1,500 customers at the beginning of the month and 90 churn during the month, leaving 1,410 customers at the end (refer to this as Cohort A). There are 200 new sales during the month.\nMonth 2: Of the 1490 customers in Cohort A, another 100 churn, leaving 1390. Of 200 in Cohort B, 15 churn, leaving 185. There are another 200 new sales this month (call this Cohort C).\n Month 3: Of the 1390 customers still in Cohort A, another 90 churn, leaving 1300. Of the 185 customers in Cohort B, 10 more churn, leaving 175. Of the 200 customers in Cohort C, 30 churn, leaving 170.\nWhat is Company A’s quarterly customer churn rate?\nGiven: Customer Churn Rate = (customer at start of quarter - customers at the end of quarter)/ customers at start of quarter.",
			choices: [
				{
					option: "A",
					correct: false,
					choiceText: "17%"
				},
				{
					option: "B",
					correct: true,
					choiceText: "13,33%"
				},
                {
                    option: "C",
                    correct: false,
                    choiceText: "13,42%"
                },
                {
                    option: "D",
                    correct: false,
                    choiceText: "14,67%"
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
