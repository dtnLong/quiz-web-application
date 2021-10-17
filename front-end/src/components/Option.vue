<template>
    <!-- <div class='text-xl'>
                     <input class='border-none outline-none' type="radio" :name="`question-${questionNumber}`" :id="option" :value="option" />

                    <label  :for="option">{{optionText}}</label> 
                                           
                </div> -->

    <!-- <div class='relative text-xl'>
        <input class='border-none outline-none opacity-0' type="radio" :id="option" :value="option" @change='$emit("optionSelected", option)' :name="`question-${questionNumber}`"/>
        <span class='absolute top-0 left-0 w-4 h-4 transform translate-y-1/2 bg-gray-300 rounded-full checked-mark'></span>
        <label class='ml-6 cursor-pointer select-none ' :for="option">{{optionText}}</label> 
                            
    </div>  -->


    <p class='w-full px-10 py-5 text-gray-900 transition-all duration-100 border border-gray-900 cursor-pointer select-none rounded-2xl hover:border-error-500 hover:bg-error-500 hover:text-white' :class="{ selected : isSelected}" @click="optionSelected" style="font-size: 18px">
        {{option}}. {{optionText}}
    </p>
    
</template>

<script>
import {computed} from 'vue'

export default {
    props: {
        optionText: String,
        questionNumber: Number,
        option: String,
        hasChecked: Boolean,  
         },
    emits: ["optionSelected"],
    setup(props, {emit}) {
        // console.log(props.hasChecked);
        // let isSelected = ref(false);
        // let hasCheckedAlias= ref(props.hasChecked); 
        let isSelected = computed(()=> props.hasChecked);
        
        
        const optionSelected = () =>{
            let selection = {option: props.option, optionText: props.optionText};
            emit("optionSelected", selection);
            //isSelected.value = true;
        }
        
        return{ optionSelected, isSelected}
    },
}
</script>
