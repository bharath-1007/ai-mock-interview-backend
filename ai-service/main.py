import traceback
from fastapi import FastAPI, HTTPException
from services.gemini_service import generate_response
from models.question_model import QuestionRequest, QuestionResponse
from services.question_service import generate_question
app = FastAPI(title="AI Mock Interview Service")
@app.get("/")
def home():
    return {
        "message": "AI Mock Interview Service is running"
    }


@app.get("/health")
def health():
    return {
        "status": "UP"
    }
    
@app.get("/test-gemini")
def test_gemini():
    try:
        prompt = "Generate one simple Java interview question."

        response = generate_response(prompt)

        return {
            "response": response
        }

    except Exception:
        traceback.print_exc()

        raise HTTPException(
            status_code=502,
            detail="Gemini API request failed"
        )
        
@app.post("/generate-question", response_model=QuestionResponse)
def generate_interview_question(request: QuestionRequest):

    try:
        question = generate_question(request)

        return {
            "questionText": question
        }

    except ValueError as e:

        raise HTTPException(
            status_code=400,
            detail=str(e)
        )

    except Exception:

        traceback.print_exc()

        raise HTTPException(
            status_code=502,
            detail="Question generation failed"
        )