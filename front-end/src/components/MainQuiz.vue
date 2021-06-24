<template>

    <section v-if="!isSubmitted" class="relative z-10 w-full">
        <!-- Time Countdown Animation ---->
        <TimeCount @toSubmit="toggleIsSubmitted"/>
        
        <!-- Question Number List ---->
        <div class="flex justify-center gap-4 mb-10">
            <QuestNumber v-for="questionItem in questionNumberList" :key="questionItem.number" :number="questionItem.number" @clickQuestion="handleQuestionClick" :state="questionItem.state"/>
        </div>


        <!-- Question Section---->        
         <div class="z-10 flex flex-col items-center w-9/12 mx-auto from-gray-300 pb-14 text-gray-50 rounded-xl bg-gradient-to-br to-gray-400 pt-9 form-container">

            <!-- Question --->
            <h1 class="text-3xl font-bold text-center ">{{questions[currentQuestionIndex].questionText}}</h1>
            

            <!-- Option List --->
            <div class='flex flex-col w-9/12 gap-8 mx-auto my-12 px-14 '>
                <Option v-for=" (option, index) in questions[currentQuestionIndex].optionText" :key="index" :optionText="option" :questionNumber="currentQuestionNumber.toString()" :optionIndex="index.toString()" :hasChecked="checkValue" />
            </div>
            

            <div class='flex items-center gap-14'>
                <a href="#"  @click="toPreviousQuestion" class='text-xl px-7 opacity-80'>Previous question</a>
                <a href="#" @click="toNextQuestion" class="inline-block py-3 text-xl font-bold rounded-full px-7 bg-gradient-to-r from-red-600 to-pink-600 ">{{buttonText}}</a>
            </div>
            
        </div>


    </section>

    <Submit v-else :submitPayLoad="submitPayLoad" @backToQuiz="toggleIsSubmitted"/>
   
</template>

<script>
import {useRouter} from 'vue-router'
import {computed, onMounted, ref, reactive, onUpdated, watchEffect} from 'vue'
import TimeCount from "./TimeCount.vue"
import QuestNumber from "./QuestNumber.vue"
import Option from "./Option.vue"
import Submit from "./Submit.vue"
export default {
    components: {TimeCount, QuestNumber, Option, Submit},
    props: {
        quizCode: String
        },
    setup(props, context) {
        const isSubmitted = ref(false);
        const currentQuestionIndex = ref(0);
        
        const questions = [
            {
                questionText: "Why are LCL lead times longer than FCL lead times 1?",
                optionText: [
                    
                    "Shipments may move on different vessels",
                    "Consolidation and deconsolidation procedures at origin and destination ports take longer",
                    "Shipments may take different routes",
                    "All of the above"

                ]
            },
            {
                questionText: "Why are LCL lead times longer than FCL lead times 2?",
                optionText: [
                    "Shipments may move on different vessels",
                    "Consolidation and deconsolidation procedures at origin and destination ports take longer",
                    "Shipments may take different routes",
                    "All of the above"

                ]
            },
            {
                questionText: "Why are LCL lead times longer than FCL lead times 3?",
                optionText: [
                    "Shipments may move on different vessels",
                    "Consolidation and deconsolidation procedures at origin and destination ports take longer",
                    "Shipments may take different routes",
                    "All of the above"

                ]
            }

        ]
        const submitPayLoad = reactive({
            quizCode: props.quizCode,
            data: [
                
            ]

        })
        const questionNumberList = ref(questions.map((element,index) => (
            index=== 0? {number: (index+1).toString(), state:'active'} :{number: (index+1).toString(), state:'default'}
            )));
        const currentQuestionNumber = computed(() => currentQuestionIndex.value + 1);
        const buttonText = computed(() => currentQuestionNumber.value === questions.length? "Go to Submit" : "Next question" );
        let isSelected;
        const updateQuestionState = (updateState) => {
            questionNumberList.value[currentQuestionIndex.value].state = updateState;
        }
        
        
      
             
        const handleQuestionClick = (el) => {
            selectAns();
            if (isSelected){
                    updateQuestionState('done');
            }else{
                updateQuestionState('default');
            }
            
            // let updateQuestionIndex = parseInt(el.innerText) - 1;
            // currentQuestionIndex.value  = updateQuestionIndex;
            currentQuestionIndex.value = parseInt(el.innerText) - 1;
            // el.classList.add("active");
            
            //questionNumberList.value[updateQuestionIndex].state = 'active';
            updateQuestionState('active');

        };

        

        const selectAns = () => {
            let optionSelected;
            let optionText;
            
            if (document.querySelector(`input[name = "question-${currentQuestionIndex.value+1}"]:checked`)){
                //TODO: get the option select by the first character of the option text. 
                optionSelected = document.querySelector(`input[name = "question-${currentQuestionIndex.value+1}"]:checked`).value;
                
                optionText = document.querySelector(`label[for="${optionSelected}"]`).innerText;
                isSelected =true;

            }else{
                optionSelected  = null;
                optionText = null;
                isSelected =false;
            }

            

             

            let answer = {
                questionText: questions[currentQuestionIndex.value].questionText,
                questionNumber: currentQuestionNumber.value,
                optionSelected,
                optionText

            }

            addToSumbitPayLoad(answer);
            
        }


        const toNextQuestion = () => {
            selectAns();

            

            if (currentQuestionIndex.value < (questions.length - 1) ){

                if (isSelected){
                    updateQuestionState('done');
                }else{
                    updateQuestionState('default');
                }
                
                currentQuestionIndex.value+=1; 
                
                updateQuestionState('active');
            }else{
                
                isSubmitted.value = true;
            }
            
            
            
        }

        const toPreviousQuestion = () => {
            selectAns();
            if (currentQuestionIndex.value > 0 ){

                if (isSelected){
                    updateQuestionState('done');
                }else{
                    updateQuestionState('default');
                }
                
                currentQuestionIndex.value-=1; 
                
                updateQuestionState('active');
            }
            
        }

        onUpdated(()=>{
            console.log(currentQuestionNumber.value);
        })

        const addToSumbitPayLoad = ({questionText, questionNumber, optionSelected, optionText}) => {
        
            let indexToAdd = questionNumber - 1;
           
           if (submitPayLoad.data[indexToAdd]){
               submitPayLoad.data[indexToAdd].optionSelected = optionSelected;
           submitPayLoad.data[indexToAdd].optionText = optionText;
           }
           else{
               submitPayLoad.data.splice(indexToAdd, 0, {questionText, questionNumber, optionSelected, optionText});
           }
           

        }
          const checkValue = computed(() => {
            
            let answer  = submitPayLoad.data.find(answer => answer.questionNumber === currentQuestionNumber.value);
           
            if (answer && answer.optionSelected){
                return answer.optionSelected;
                
            }else{
                return "init";
            }
            
        })
        const toggleIsSubmitted = (state) => {
            if (state) {selectAns()};
            isSubmitted.value = state;
        }
        
        return{handleQuestionClick, questions, currentQuestionIndex, selectAns, toNextQuestion, toPreviousQuestion, questionNumberList, currentQuestionNumber, checkValue, buttonText, isSubmitted, submitPayLoad, toggleIsSubmitted}
    },
}
</script>