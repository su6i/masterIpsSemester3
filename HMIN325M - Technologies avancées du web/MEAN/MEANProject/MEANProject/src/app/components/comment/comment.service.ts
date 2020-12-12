import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Comment } from './comment.model';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private comments: Comment[] = [];
  private commentsUpdated = new Subject<Comment[]>();

  constructor() { }

  getComments(){
    return [...this.comments];
  }

  getCommentUpdateListener(){
    return this.commentsUpdated.asObservable();
  }

  addComment(title: string, content: string){
    const comment: Comment = {title: title, content: content};
    this.comments.push(comment);
    this.commentsUpdated.next([...this.comments]);
  }

}
