<template>
    <h1 class="mb-12 text-xl font-semibold leading-8 whitespace-pre-line select-none"> Question {{questionNumber}}: 
        <span v-for="(text, index) in questionFormat"  :key="index" v-html="text"></span>
    </h1>
    
</template>

<script>
import {ref, onMounted, computed} from 'vue'
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
                
                return`<img class='w-1/2 mx-auto' src='${chunk}'/>`;
            }else{
                return chunk;
            }
            })
            
        })
        console.log(questionFormat.value)
    
        return{questionFormat};
         
    }
}
</script>