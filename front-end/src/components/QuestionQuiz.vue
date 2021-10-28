<template>
    <div class="text-xl font-medium leading-8 select-none">Question {{questionNumber}}: 
        <p v-for="(text, index) in questionFormat"  class="mt-1 whitespace-preline " :key="index" v-html="text"></p>
    </div>
    
</template>

<script>
import {ref, computed} from 'vue'
export default {
    props: {questionNumber: Number, questionText: String},
    setup(props){
        const arrayOfLinks = ref([]);
         const LINK_DETECTION_REGEX = /(([a-z]+:\/\/)?(([a-z0-9\-]+\.)+([a-z]{2}|aero|arpa|biz|com|coop|edu|gov|info|int|jobs|mil|museum|name|nato|net|org|pro|travel|local|internal))(:[0-9]{1,5})?(\/[a-z0-9_\-\.~]+)*(\/([a-z0-9_\-\.]*)(\?[a-z0-9+_\-\.%=&amp;]*)?)?(#[a-zA-Z0-9!$&'()*+.=-_~:@/?]*)?)(\s+|$)/gi
        
        let questionFormat = computed(() =>{
           
            let textBreakDown = props.questionText.split('\n');
            return textBreakDown.map(chunk => {
            if (LINK_DETECTION_REGEX.test(chunk)) {
                // arrayOfLinks.value.push(chunk);
                
                return`<img class='w-full mx-auto xl:w-10/12' src='${chunk}'/>`;
            }else if (chunk.includes('\"')) {
                let firstIndex = chunk.indexOf('\"');
                
                let secondIndex = chunk.indexOf('\"', firstIndex+1); 
                
                let boldText = "<span class='text-error-500'>" + chunk.slice(firstIndex+1, secondIndex) + "</span>";
                
                
                let newChunk = chunk.slice(0, firstIndex) + boldText + chunk.slice(secondIndex+1);
                return newChunk;

            }
            else{
                return chunk;
            }
            })
            
        })
        // console.log(questionFormat.value)
    
        return{questionFormat};
         
    }
}
</script>