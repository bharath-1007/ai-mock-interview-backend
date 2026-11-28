from pydantic import BaseModel
from typing import Optional, Literal
class QuestionRequest(BaseModel):
    interviewType: Literal["TECHNICAL", "RESUME_BASED"]
    topic: Optional[str] = None
    difficulty: Literal["EASY", "MEDIUM", "HARD"]
    resumeText: Optional[str] = None

class QuestionResponse(BaseModel):
    questionText: str